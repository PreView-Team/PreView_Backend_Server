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
    private long id;
    private String mentoNickname;
    private LocalDateTime createTime;
    private boolean status;

    public static FormAllGetResponseDto from(Form form){
        if (form == null) return null;
        return FormAllGetResponseDto.builder()
                .id(form.getId())
                .createTime(form.getCreatedDate())
                .mentoNickname(form.getUser().getNickname())
                .status(form.isStatus()).build();
    }
}
