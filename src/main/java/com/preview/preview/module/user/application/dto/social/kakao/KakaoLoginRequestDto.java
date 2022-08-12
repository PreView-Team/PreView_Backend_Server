package com.preview.preview.module.user.application.dto.social.kakao;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;

public class KakaoLoginRequestDto {
    private String kakaoRefreshToken;
    public String getKakaoRefreshToken(){
        if (kakaoRefreshToken == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return kakaoRefreshToken;
    }
}
