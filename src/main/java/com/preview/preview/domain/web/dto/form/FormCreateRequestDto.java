package com.preview.preview.domain.web.dto.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormCreateRequestDto {
    private long postId;
    private String name;
    private String phoneNumber;
    private String university;
    private String wantedJob;
    private String context;
}
