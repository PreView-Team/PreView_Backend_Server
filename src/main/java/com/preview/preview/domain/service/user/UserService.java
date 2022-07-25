package com.preview.preview.domain.service.user;

import com.preview.preview.domain.user.User;
import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginInfoDto;
import com.preview.preview.domain.web.dto.social.kakao.KakaoSignupRequestDto;
import com.preview.preview.domain.web.dto.user.*;

import java.util.Optional;

public interface UserService {

    // 회원가입 메소드
    public SignupResponseDto signup(KakaoSignupRequestDto kakaoSignupRequestDto, KakaoLoginInfoDto kakaoLoginInfoDto);
    // 중복 닉네임 체크 메소드
    public VaildedNicknameDto checkNicknameDuplicate(String name);
    // 유저 조회 메소드 (수정 필요)
    public UserDto getMyUserWithAuthorities();
    // 카카오 id로 유저 조회 메소드
    public Optional<User> findByKakaoId(Long id);
    // 이메일 중복 체크 메소드
    public boolean existedByEmail(String email);
    // 카카오 id로 유저 업데이트
    public UserUpdateResponseDto updateUserByKakaoId(UserUpdateRequestDto userUpdateRequestDto, long kakaoId);
    // 카카오 id로 유저 삭제
    public UserDeleteResponseDto deleteUserByKakaoId(long kakaoId);
}
