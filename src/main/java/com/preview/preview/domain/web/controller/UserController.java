package com.preview.preview.domain.web.controller;

import com.preview.preview.domain.service.social.kakao.KakaoServiceImpl;
import com.preview.preview.domain.service.user.UserServiceImpl;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginInfoDto;
import com.preview.preview.domain.web.dto.social.kakao.KakaoSignupRequestDto;
import com.preview.preview.domain.web.dto.user.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


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
        return ResponseEntity.ok(userService.signup(kakaoSignupRequestDto, kakaoLoginInfoDto));
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/user")
    public ResponseEntity<UserUpdateResponseDto> updateUser(
            @AuthenticationPrincipal User user,
            @RequestBody UserUpdateRequestDto userUpdateRequestDto){
        return ResponseEntity.ok(userService.updateUserByKakaoId(userUpdateRequestDto, user.getKakaoId()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/user")
    public ResponseEntity<UserDeleteResponseDto> deleteUser(
            @AuthenticationPrincipal User user){
        return ResponseEntity.ok(userService.deleteUserByKakaoId(user.getKakaoId()));
    }

}