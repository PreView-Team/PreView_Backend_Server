package com.preview.preview.module.user.application.dto;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.job.domain.Job;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public class UserUpdateRequestDto {
    private Set<Job> jobDtoSet;
    private String nickname;

    public Set<Job> getJobDtoSet(){
        if (jobDtoSet == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return jobDtoSet;
    }

    public String getNickname(){
        if (nickname == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return nickname;
    }

}
