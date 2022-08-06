package com.preview.preview.module.form.application.dto;

import com.preview.preview.module.job.application.dto.JobDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FormUpdateRequestDto {
    private String name;
    private String phoneNumber;
    private String contents; // 상담 받고 싶은 내용
    private String local;
    private String jobNames;
}
