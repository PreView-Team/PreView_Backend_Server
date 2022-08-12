package com.preview.preview.module.form.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preview.preview.module.form.domain.Form;
import com.preview.preview.module.form.domain.MentorForm;
import com.preview.preview.module.job.domain.Job;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Builder
public class FormByMentorGetResponseDto {
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime createTime;
    private String username;
    private String phoneNumber;
    private String jobNames;
    private String contents; // 상담 받고 싶은 내용
    private String fcmToken;
    private String local;

    public static FormByMentorGetResponseDto form(MentorForm form){
        if (form == null) return null;
        return FormByMentorGetResponseDto.builder()
                .contents(form.getContent())
                .createTime(form.getCreatedDate())
                .phoneNumber(form.getPhoneNumber())
                .username(form.getName())
                .jobNames(form.getLikedJobs())
                .fcmToken(form.getFcmToken())
                .local(form.getLocal())
                .status(form.getStatus()).build();

    }
}
