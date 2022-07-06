package com.preview.preview.domain.web.dto.social.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.preview.preview.domain.enterprise.Enterprise;
import com.preview.preview.domain.job.Job;
import com.preview.preview.domain.web.dto.enterprise.EnterpriseDto;
import com.preview.preview.domain.web.dto.job.JobDto;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class KakaoSignupRequestDto {
    private String token; // access token
    private String nickname; // 닉네임
    private List<JobDto> jobNames; // 직무 이름 리스트
    private List<EnterpriseDto> enterpriseNames; // 기업 리스트
}
