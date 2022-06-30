package com.preview.preview.domain.web.dto.social.kakao;

import lombok.Getter;

@Getter
public class KakaoLoginRequestDto {
    private String token; // access token
    private String nickname; // 닉네임
}
