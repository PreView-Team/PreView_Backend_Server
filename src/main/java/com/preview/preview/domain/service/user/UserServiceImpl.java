package com.preview.preview.domain.service.user;

import com.preview.preview.domain.authority.Authority;
import com.preview.preview.domain.enterprise.Enterprise;
import com.preview.preview.domain.exception.CustomException;
import com.preview.preview.domain.exception.ErrorCode;
import com.preview.preview.domain.job.Job;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.user.UserRepository;
import com.preview.preview.domain.web.dto.enterprise.EnterpriseDto;
import com.preview.preview.domain.web.dto.job.JobDto;
import com.preview.preview.domain.web.dto.user.*;
import com.preview.preview.global.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public VaildedNicknameDto checkNicknameDuplicate(String name) {
        VaildedNicknameDto vaildedNicknameDto = new VaildedNicknameDto();
        Boolean isExisted = userRepository.existsByNickname(name);
        vaildedNicknameDto.setIsValidNickname(isExisted);
        return vaildedNicknameDto;
    }

    @Transactional
    public UserDto signup(UserDto userDto){

        if (userRepository.findOneWithAuthoritiesByKakaoId(userDto.getKakaoId()).orElse(null) != null){
            throw new CustomException(ErrorCode.DUPLICATE_SIGNUP_RESOURCE);
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Set<Job> jobs = new HashSet<>();
        for (JobDto s : userDto.getJobDtoSet()){
            jobs.add(Job.builder().name(s.getJobName()).build());
        }

        User user = User.builder()
                .password(passwordEncoder.encode("xxxx")) // 추 후 확장, 지금 서비스에선 필요 x
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .email(userDto.getEmail())
                .likedJobs(jobs)
                .likedEnterprises(null)
                .kakaoId(userDto.getKakaoId())
                .activated(true)
                .build();

        return userDto.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String username){
        return null;
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities(){
        return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByKakaoId).orElse(null));
    }

    @Override
    public Optional<User> findByIdPw(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByKakaoId(Long id){
        return Optional.ofNullable(userRepository.findOneWithAuthoritiesByKakaoId(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_USER_ID)));
    }

    public boolean existedByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public UserDeleteResponseDto deleteUserByKakaoId(UserDeleteRequestDto userDeleteRequestDto){
        UserDeleteResponseDto userDeleteResponseDto = new UserDeleteResponseDto();

        userRepository.findByKakaoId(userDeleteRequestDto.getKakaoId()).filter(
                user -> user.getDeletedDate() == null
        ).map(user -> {
            user.deleteUser();
            userRepository.save(user);
            userDeleteResponseDto.setResult("삭제되었습니다.");
            return userDeleteResponseDto;
        }).orElseThrow(()-> new CustomException(ErrorCode.NOT_DELETE_USER_RESOURCE));

        return userDeleteResponseDto;
    }

    @Transactional
    public UserUpdateResponseDto updateUserByKakaoId(UserUpdateRequestDto userUpdateRequestDto){
        UserUpdateResponseDto userUpdateResponseDto = new UserUpdateResponseDto();
        Optional<User> user = Optional.ofNullable(userRepository.findByKakaoId(userUpdateRequestDto.getKakaoId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_USER_ID)));
        user.get().setLikedJobs(userUpdateRequestDto.getJobDtoSet());

        try {
            userRepository.save(user.get());
        } catch (JpaObjectRetrievalFailureException ex){
            throw new CustomException(ErrorCode.NOT_EXISTED_LIKE_JOB);
        }

        userUpdateResponseDto.setId(user.get().getId());
        userUpdateResponseDto.setResult("유저 정보를 수정하였습니다.");
        return userUpdateResponseDto;
    }

}
