package com.preview.preview.module.form.application.dto;

import com.preview.preview.module.form.domain.Form;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FormGetResponseDto {
    private long id;
    private String mentoNickname;
    private LocalDateTime createTime;
    private String name;
    private String phoneNumber;
    private String university;
    private String wantedJob;
    private String context; // 상담 받고 싶은 내용
    private boolean status;

    public static FormGetResponseDto from(Form form){
        if (form == null) return null;
        return FormGetResponseDto.builder()
                .id(form.getId())
                .mentoNickname(form.getUser().getNickname())
                .createTime(form.getCreatedDate())
                .name(form.getName())
                .phoneNumber(form.getPhoneNumber())
                .university(form.getUniversity())
                .wantedJob(form.getWantedJob())
                .context(form.getContext())
                .status(form.isStatus())
                .build();
    }
}
