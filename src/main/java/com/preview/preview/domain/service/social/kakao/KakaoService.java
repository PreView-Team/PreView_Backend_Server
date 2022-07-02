package com.preview.preview.domain.service.social.kakao;

import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginInfoDto;
import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginRequestDto;

public interface KakaoService {
    public KakaoLoginInfoDto getProfile(String token);
}
