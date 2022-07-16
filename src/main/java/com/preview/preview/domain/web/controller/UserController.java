package com.preview.preview.domain.web.controller;

import com.preview.preview.domain.service.social.kakao.KakaoServiceImpl;
import com.preview.preview.domain.service.user.UserServiceImpl;
import com.preview.preview.domain.web.dto.authority.AuthorityResponseDto;
import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginInfoDto;
import com.preview.preview.domain.web.dto.social.kakao.KakaoSignupRequestDto;
import com.preview.preview.domain.web.dto.user.SignupResponseDto;
import com.preview.preview.domain.web.dto.user.UserDto;
import com.preview.preview.domain.web.dto.user.VaildedNicknameDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;
    private final KakaoServiceImpl kakaoService;

    public UserController(UserServiceImpl userService, KakaoServiceImpl kakaoService) {
        this.userService = userService;
        this.kakaoService = kakaoService;
    }

    @PostMapping("/user/kakao/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody KakaoSignupRequestDto kakaoSignupRequestDto){
        KakaoLoginInfoDto kakaoLoginInfoDto = kakaoService.getProfile(kakaoSignupRequestDto.getKakaoAccessToken());

        UserDto userDto = new UserDto();
        userDto.setNickname(kakaoSignupRequestDto.getNickname());
        userDto.setKakaoId(kakaoLoginInfoDto.getId());
        userDto.setJobDtoSet(Set.copyOf(kakaoSignupRequestDto.getJobNames()));
        userDto.setEmail(kakaoSignupRequestDto.getEmail());
        userService.signup(userDto);

        SignupResponseDto signupResponseDto = new SignupResponseDto();
        signupResponseDto.setResult("가입 성공");
        return ResponseEntity.ok(signupResponseDto);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<UserDto> getMyUserInfo(){
        UserDto userDto = userService.getMyUserWithAuthorities();
        return ResponseEntity.ok(userDto);
    }



    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username){
        UserDto userDto = userService.getUserWithAuthorities(username);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/user/nickname/{nickname}")
    public ResponseEntity<VaildedNicknameDto> isExisedNickname(@PathVariable String nickname){
        VaildedNicknameDto vaildedNicknameDto = userService.checkNicknameDuplicate(nickname);
        return ResponseEntity.ok(vaildedNicknameDto);
    }

}