package com.preview.preview.module.form.application.dto;

import com.preview.preview.module.form.domain.Form;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FormUpdateResponseDto {
    private String result;

    public static FormUpdateResponseDto from(Form form){
        if (form == null) return null;
        return FormUpdateResponseDto.builder()
                .result("신청서 수정 성공")
                .build();
    }
}
