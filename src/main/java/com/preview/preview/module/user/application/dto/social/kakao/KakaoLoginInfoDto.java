package com.preview.preview.module.user.application.dto.social.kakao;


import lombok.Getter;

@Getter
public class KakaoLoginInfoDto {
    private Long id; // 회원번호
    private KakaoAccountDto kakao_account;
    private KakaoProfileDto properties;
}
