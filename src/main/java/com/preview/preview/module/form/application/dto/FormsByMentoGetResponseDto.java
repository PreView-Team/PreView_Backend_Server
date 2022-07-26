package com.preview.preview.module.form.application.dto;


import com.preview.preview.module.form.domain.Form;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FormsByMentoGetResponseDto {
    private long id;
    private String username;
    private LocalDateTime createTime;
    private boolean status;

    public static FormsByMentoGetResponseDto from(Form form){
        if (form == null) return null;
        return FormsByMentoGetResponseDto.builder()
                .id(form.getId())
                .createTime(form.getCreatedDate())
                .username(form.getName())
                .status(form.isStatus()).build();
    }
}
