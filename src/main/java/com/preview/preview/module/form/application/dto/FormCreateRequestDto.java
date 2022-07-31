package com.preview.preview.module.form.application.dto;

import com.preview.preview.module.job.application.dto.JobDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FormCreateRequestDto {
    private long postId;
    private String name;
    private String phoneNumber;
    private String contents;
}
