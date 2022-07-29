package com.preview.preview.module.form.application.dto;

import com.preview.preview.module.form.domain.Form;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FormCreateResponseDto {
    private String result;

    public static FormCreateResponseDto from(Form form){
        if (form == null) return null;
        return FormCreateResponseDto.builder()

                .result("멘티 신청 완료 되었습니다.")
                .build();
    }
}
