package com.preview.preview.module.form.application.dto;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.job.application.dto.JobDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class FormUpdateRequestDto {
    private String name;
    private String phoneNumber;
    private String contents; // 상담 받고 싶은 내용
    private String local;
    private String jobNames;

    public String getName() {
        if (name == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return name;
    }

    public String getPhoneNumber() {
        if (phoneNumber == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return phoneNumber;
    }

    public String getContents() {
        if (contents == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return contents;
    }

    public String getLocal() {
        if (local == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return local;
    }

    public String getJobNames() {
        if (jobNames == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return jobNames;
    }
}
