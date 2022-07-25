package com.preview.preview.global.auth.jwt;

import com.preview.preview.domain.exception.CustomException;
import com.preview.preview.domain.exception.ErrorCode;
import com.preview.preview.domain.service.social.kakao.KakaoServiceImpl;
import com.preview.preview.domain.service.user.UserServiceImpl;
import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginInfoDto;
import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginRequestDto;
import com.preview.preview.domain.web.dto.token.TokenDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthRestController{

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final KakaoServiceImpl kakaoService;
    private final UserServiceImpl userService;

    public AuthRestController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, KakaoServiceImpl kakaoService, UserServiceImpl userService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.kakaoService = kakaoService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody KakaoLoginRequestDto kakaoLoginRequestDto){

        KakaoLoginInfoDto kakaoLoginInfoDto = kakaoService.getProfile(kakaoLoginRequestDto.getKakaoAccessToken());

        if (userService.findByKakaoId(kakaoLoginInfoDto.getId()).get().getDeletedDate() != null){
            throw new CustomException(ErrorCode.DELETE_USER_RESOURCE);
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(kakaoLoginInfoDto.getId().toString(), "xxxx");

        Authentication authentication;
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (BadCredentialsException exception){
            throw new CustomException(ErrorCode.NOT_EXISTED_USER_ID);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication, kakaoLoginInfoDto.getId());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer "+jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
}
