package com.preview.preview.domain.web.dto.form;

import com.preview.preview.domain.form.Form;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FormCreateResponseDto {
    private long id;
    private String result;

    public static FormCreateResponseDto from(Form form){
        if (form == null) return null;
        return FormCreateResponseDto.builder()
                .id(form.getId())
                .result("멘티 신청 완료 되었습니다.")
                .build();
    }
}
