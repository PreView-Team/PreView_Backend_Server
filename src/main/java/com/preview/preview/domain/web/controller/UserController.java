package com.preview.preview.domain.web.controller;

import com.preview.preview.domain.service.social.KakaoServiceImpl;
import com.preview.preview.domain.service.user.UserServiceImpl;
import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginInfoDto;
import com.preview.preview.domain.web.dto.social.kakao.KakaoSignupRequestDto;
import com.preview.preview.domain.web.dto.user.UserDto;
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

    @PostMapping("/user/kakao/signup")
    public ResponseEntity<String> signup(@RequestBody KakaoSignupRequestDto kakaoSignupRequestDto){
        KakaoLoginInfoDto kakaoLoginInfoDto = kakaoService.getProfile(kakaoSignupRequestDto.getToken());

        UserDto userDto = new UserDto();
        userDto.setNickname(kakaoSignupRequestDto.getNickname());
        userDto.setKakaoId(kakaoLoginInfoDto.getId());
         // 회원가입

        //Job job = jobRepository.findByName(token.getJobName());
//        for (String s : token.getJobName()){
//            Optional<User> user = userRepository.findOneWithAuthoritiesByKakaoId(userLoginDto.getKakaoId());
//            Job job = jobRepository.findByName(s);
//            likedJobRepository.save(LikedJob.builder().job(job).user(user.get()).build());
//        }

        userService.save(userDto);
        return ResponseEntity.ok("가입 성공");
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

    @GetMapping("/user/nickname/{nickname}")
    public ResponseEntity<Boolean> isExisedNickname(@PathVariable String nickname){
        return userService.checkNicknameDuplicate(nickname);
    }

}