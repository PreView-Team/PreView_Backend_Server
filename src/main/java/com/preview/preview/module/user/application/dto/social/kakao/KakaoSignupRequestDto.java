package com.preview.preview.module.user.application.dto.social.kakao;
import com.preview.preview.core.exception.CustomException;
import com.preview.preview.module.job.application.dto.JobDto;

import java.util.List;

public class KakaoSignupRequestDto {

    private String kakaoAccessToken; // access token

    private String nickname; // 닉네임

    private List<JobDto> jobNames; // 직무 이름 리스트

    private String email; // 이메일

    public String getKakaoAccessToken(){
        if (kakaoAccessToken == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return kakaoAccessToken;
    }

    public String getNickname(){
        if (nickname == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return nickname;
    }

    public List<JobDto> getJobNames(){
        if (jobNames == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return jobNames;
    }

    public String getEmail(){
        return email;
    }

}
