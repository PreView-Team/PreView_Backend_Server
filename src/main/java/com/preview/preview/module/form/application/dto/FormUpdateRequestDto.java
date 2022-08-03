package com.preview.preview.module.form.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormUpdateRequestDto {
    private String name;
    private String phoneNumber;
    private String contents; // 상담 받고 싶은 내용
    private String local;
}
