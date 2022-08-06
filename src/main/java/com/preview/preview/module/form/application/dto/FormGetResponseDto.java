package com.preview.preview.module.form.application.dto;

import com.preview.preview.module.form.domain.Form;
import com.preview.preview.module.form.domain.MentorForm;
import com.preview.preview.module.job.domain.Job;
import com.preview.preview.module.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class FormGetResponseDto {
    private long formId;
    private String mentorNickname;
    private LocalDateTime createTime;
    private String name;
    private String phoneNumber;
    private String jobNames;
    private String contents; // 상담 받고 싶은 내용
    private String local;
    private String status;
    private String fcmToken;

    public static FormGetResponseDto from(Form form){
        if (form == null) return null;
        return FormGetResponseDto.builder()
                .formId(form.getId())
                .mentorNickname(form.getMentorNicname())
                .createTime(form.getCreatedDate())
                .name(form.getName())
                .phoneNumber(form.getPhoneNumber())
                .jobNames(form.getLikeJobs())
                .contents(form.getContent())
                .status(form.getStatus())
                .local(form.getLocal())
                .fcmToken(form.getFcmToken())
                .build();
    }
}
