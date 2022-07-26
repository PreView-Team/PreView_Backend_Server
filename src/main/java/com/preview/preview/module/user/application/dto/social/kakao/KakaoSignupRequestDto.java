package com.preview.preview.module.user.application.dto.social.kakao;
import com.preview.preview.module.job.application.dto.JobDto;
import lombok.Getter;

import java.util.List;

@Getter
public class KakaoSignupRequestDto {
    private String kakaoAccessToken; // access token
    private String nickname; // 닉네임
    private String email; // 이메일
    private List<JobDto> jobNames; // 직무 이름 리스트
}
