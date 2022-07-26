package com.preview.preview.domain.web.dto.form;

import com.preview.preview.domain.form.Form;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FormUpdateResponseDto {
    private long id;
    private String result;

    public static FormUpdateResponseDto from(Form form){
        if (form == null) return null;
        return FormUpdateResponseDto.builder()
                .id(form.getId())
                .result("신청서 수정 성공")
                .build();
    }
}
