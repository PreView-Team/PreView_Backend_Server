package com.preview.preview.module.user.application.dto.social.kakao;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;

public class KakaoLoginRequestDto {
    private String kakaoAccessToken;

    public String getKakaoAccessToken(){
        if (kakaoAccessToken == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return kakaoAccessToken;
    }
}
