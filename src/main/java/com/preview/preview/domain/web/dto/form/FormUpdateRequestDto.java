package com.preview.preview.domain.web.dto.form;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FormUpdateRequestDto {
    private String name;
    private String phoneNumber;
    private String university;
    private String wantedJob;
    private String context; // 상담 받고 싶은 내용
}
