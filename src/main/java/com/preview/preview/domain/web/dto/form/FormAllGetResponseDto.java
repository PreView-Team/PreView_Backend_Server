package com.preview.preview.domain.web.dto.form;

import com.preview.preview.domain.form.Form;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FormAllGetResponseDto {
    private String mentoNickname;
    private LocalDateTime createTime;
    private boolean status;

    public static FormAllGetResponseDto from(Form form){
        if (form == null) return null;
        return FormAllGetResponseDto.builder()
                .createTime(form.getCreatedDate())
                .mentoNickname(form.getUser().getNickname())
                .status(form.isStatus()).build();
    }
}
