package com.preview.preview.module.form.application.dto;

import com.preview.preview.module.form.domain.Form;
import com.preview.preview.module.job.domain.Job;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
public class FormByMentorGetResponseDto {
    private String status;
    private LocalDateTime createTime;
    private String username;
    private String phoneNumber;
    private Set<Job> jobNames;
    private String contents; // 상담 받고 싶은 내용

    public static FormByMentorGetResponseDto form(Form form){
        if (form == null) return null;
        return FormByMentorGetResponseDto.builder()
                .contents(form.getContent())
                .createTime(form.getCreatedDate())
                .phoneNumber(form.getPhoneNumber())
                .username(form.getName())
                .jobNames(form.getUser().getLikedJobs())
                .status(form.getStatus()).build();
    }
}
