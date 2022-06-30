package com.preview.preview.domain.web.controller;

import com.preview.preview.domain.service.social.KakaoServiceImpl;
import com.preview.preview.domain.service.user.UserServiceImpl;
import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginInfoDto;
import com.preview.preview.domain.web.dto.user.UserDto;
import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginRequestDto;
import com.preview.preview.domain.web.dto.user.UserLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;
    private final KakaoServiceImpl kakaoService;

    public UserController(UserServiceImpl userService, KakaoServiceImpl kakaoService) {
        this.userService = userService;
        this.kakaoService = kakaoService;
    }

    @PostMapping("/user/kakao/login")
    public ResponseEntity<UserLoginDto> login(@RequestBody KakaoLoginRequestDto token){
        KakaoLoginInfoDto kakaoLoginInfoDto = kakaoService.getProfile(token);
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setNickname(token.getNickname());
        userLoginDto.setKakaoId(kakaoLoginInfoDto.getId());
        return ResponseEntity.ok(userService.save(userLoginDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<UserDto> getMyUserInfo(){
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
    }

}