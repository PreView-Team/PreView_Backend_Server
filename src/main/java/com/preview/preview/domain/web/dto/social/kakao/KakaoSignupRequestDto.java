package com.preview.preview.domain.web.dto.social.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class KakaoSignupRequestDto {
    private String token; // access token
    private String nickname; // 닉네임
    private List<String> jobNames; // 직무 이름 리스트
}
