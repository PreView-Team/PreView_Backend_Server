package com.preview.preview.module.mentor.application.dto;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.job.domain.MentorJob;

import java.util.Set;

public class MentorUpdateRequestDto {
    private String nickname;
    private String contents;
    private Set<MentorJob> jobDtoSet;

    public String getNickname(){
        if (nickname == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return nickname;
    }

    public String getContents(){
        if (contents == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return contents;
    }

    public Set<MentorJob> getJobDtoSet(){
        if (jobDtoSet == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return jobDtoSet;
    }
}
