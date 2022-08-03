package com.preview.preview.module.user.application;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.auth.domain.Authority;
import com.preview.preview.module.job.application.dto.JobDto;
import com.preview.preview.module.job.domain.Job;
import com.preview.preview.module.user.application.dto.*;
import com.preview.preview.module.user.application.dto.social.kakao.KakaoLoginInfoDto;
import com.preview.preview.module.user.application.dto.social.kakao.KakaoSignupRequestDto;
import com.preview.preview.module.user.domain.User;
import com.preview.preview.module.user.domain.UserRepository;
import com.preview.preview.util.SecurityUtil;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public VaildedNicknameDto checkNicknameDuplicate(String name) {
        return VaildedNicknameDto.from(userRepository.existsByNickname(name));
    }

    @Transactional
    public SignupResponseDto signup(KakaoSignupRequestDto kakaoSignupRequestDto, KakaoLoginInfoDto kakaoLoginInfoDto){

        if (userRepository.findOneWithAuthoritiesByKakaoId(kakaoLoginInfoDto.getId()).orElse(null) != null){
            throw new CustomException(ErrorCode.DUPLICATE_SIGNUP_RESOURCE);
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Set<Job> jobs = new HashSet<>();

        for (JobDto s : kakaoSignupRequestDto.getJobNames()){
            jobs.add(Job.builder().name(s.getJobName()).build());
        }

        User user = User.builder()
                .password(passwordEncoder.encode("xxxx")) // 추 후 확장, 지금 서비스에선 필요 x
                .nickname(kakaoSignupRequestDto.getNickname())
                .authorities(Collections.singleton(authority))
                .email(kakaoSignupRequestDto.getEmail())
                .likedJobs(jobs)
                .likedEnterprises(null)
                .kakaoId(kakaoLoginInfoDto.getId())
                .activated(true)
                .build();

        return SignupResponseDto.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String username){
        return null;
    }

    @Transactional
    public UserInfomationGetRequestDto getUserProfileBykakaoId(long kakaoId){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        return UserInfomationGetRequestDto.from(user);
    }

    @Transactional
    public Optional<User> findByKakaoId(Long id){
        return Optional.ofNullable(userRepository.findOneWithAuthoritiesByKakaoId(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_USER_ID)));
    }

    @Transactional
    public boolean existedByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public UserDeleteResponseDto deleteUserByKakaoId(long kakaoId){
        return userRepository.findByKakaoId(kakaoId).filter(
                user -> user.getDeletedDate() == null
        ).map(user -> {
            user.deleteUser();
            userRepository.save(user);
            return UserDeleteResponseDto.from(user);
        }).orElseThrow(()-> new CustomException(ErrorCode.NOT_DELETE_USER_RESOURCE));
    }

    @Transactional
    public UserUpdateResponseDto updateUserByKakaoId(UserUpdateRequestDto userUpdateRequestDto, long kakaoId){
        Optional<User> user = Optional.ofNullable(userRepository.findByKakaoId(kakaoId).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_USER_ID)));
        user.get().setLikedJobs(userUpdateRequestDto.getJobDtoSet());
        user.get().setUserNickname(userUpdateRequestDto.getNickname());

        try {
            userRepository.save(user.get());
        } catch (JpaObjectRetrievalFailureException ex){
            throw new CustomException(ErrorCode.NOT_EXISTED_LIKE_JOB);
        }
        return UserUpdateResponseDto.from(user.get());
    }

    @Transactional
    public UserNicknameUpdateResponseDto updateNicknameBykakaoId(UserNicknameUpdateRequestDto userNicknameUpdateRequestDto, long kakaoId){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        user.setUserNickname(userNicknameUpdateRequestDto.getNickname());
        userRepository.save(user);
        return UserNicknameUpdateResponseDto.builder().result("닉네임 변경 성공").build();
    }

}
