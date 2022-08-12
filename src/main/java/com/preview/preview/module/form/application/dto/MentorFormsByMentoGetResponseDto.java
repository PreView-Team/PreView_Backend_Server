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
public class MentorFormsByMentoGetResponseDto {
    private long formId;
    private String username;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime createTime;
    private String status;

    public static MentorFormsByMentoGetResponseDto from(MentorForm form){
        if (form == null) return null;
        return MentorFormsByMentoGetResponseDto.builder()
                .formId(form.getId())
                .createTime(form.getCreatedDate())
                .username(form.getName())
                .status(form.getStatus()).build();
    }
}
