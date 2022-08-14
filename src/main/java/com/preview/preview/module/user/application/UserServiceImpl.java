package com.preview.preview.module.user.application;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.auth.domain.Authority;
import com.preview.preview.module.job.application.dto.JobDto;
import com.preview.preview.module.job.domain.Job;
import com.preview.preview.module.mentor.domain.MentorRepository;
import com.preview.preview.module.user.application.dto.*;
import com.preview.preview.module.user.application.dto.social.kakao.KakaoLoginInfoDto;
import com.preview.preview.module.user.application.dto.social.kakao.KakaoSignupRequestDto;
import com.preview.preview.module.user.domain.User;
import com.preview.preview.module.user.domain.UserRepository;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MentorRepository mentorRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, MentorRepository mentorRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mentorRepository = mentorRepository;
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

        if (userRepository.existsByNickname(kakaoSignupRequestDto.getNickname()).equals(true)) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME_RESOURCE);
        }

        if (mentorRepository.existsMentorByNickname(kakaoSignupRequestDto.getNickname()).equals(true)) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME_RESOURCE);
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
    public boolean findByKakaoId(Long id) {
        if (!userRepository.existsByKakaoId(id)){
            throw new CustomException(ErrorCode.NOT_EXISTED_USER_ID);
        }

        User user = userRepository.findByKakaoId(id).get();

        if(!Objects.isNull(user.getDeletedDate())) return false;

        return true;
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

        if (userRepository.existsByNickname(userUpdateRequestDto.getNickname()).equals(true) && user.get().getNickname().equals(userUpdateRequestDto.getNickname()) == false) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME_RESOURCE);
        }

        if (mentorRepository.existsMentorByNickname(userUpdateRequestDto.getNickname()).equals(true) && user.get().getNickname().equals(userUpdateRequestDto.getNickname()) == false) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME_RESOURCE);
        }

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

        if (userRepository.existsByNickname(userNicknameUpdateRequestDto.getNickname())) throw new CustomException(ErrorCode.DUPLICATE_NICKNAME_RESOURCE);

        if (mentorRepository.existsMentorByNickname(userNicknameUpdateRequestDto.getNickname())) throw new CustomException(ErrorCode.DUPLICATE_NICKNAME_RESOURCE);

        user.setUserNickname(userNicknameUpdateRequestDto.getNickname());
        userRepository.save(user);
        return UserNicknameUpdateResponseDto.builder().result("닉네임 변경 성공").build();
    }

}
