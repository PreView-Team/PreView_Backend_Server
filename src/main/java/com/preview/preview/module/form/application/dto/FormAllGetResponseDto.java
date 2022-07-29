package com.preview.preview.module.form.application.dto;


import com.preview.preview.module.form.domain.Form;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FormAllGetResponseDto {
    private long formId;
    private String mentorNickname;
    private LocalDateTime createTime;
    private String status;

    public static FormAllGetResponseDto from(Form form){
        if (form == null) return null;
        return FormAllGetResponseDto.builder()
                .formId(form.getId())
                .createTime(form.getCreatedDate())
                .mentorNickname(form.getUser().getNickname())
                .status(form.getStatus()).build();
    }
}
