package com.preview.preview.domain.web.dto.social.kakao;


import lombok.Getter;

@Getter
public class KakaoLoginInfoDto {
    private Long id; // 회원번호
    private KakaoAccountDto kakao_account;
    private KakaoProfileDto properties;
}
