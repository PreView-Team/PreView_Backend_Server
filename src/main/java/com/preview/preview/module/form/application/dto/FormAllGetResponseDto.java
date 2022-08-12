package com.preview.preview.module.form.application.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.preview.preview.module.form.domain.Form;
import com.preview.preview.module.form.domain.MentorForm;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime createTime;
    private String status;

    public static FormAllGetResponseDto from(Form form){
        if (form == null) return null;

        return FormAllGetResponseDto.builder()
                .formId(form.getId())
                .createTime(form.getCreatedDate())
                .mentorNickname(form.getMentorNicname())
                .status(form.getStatus()).build();
    }
}
