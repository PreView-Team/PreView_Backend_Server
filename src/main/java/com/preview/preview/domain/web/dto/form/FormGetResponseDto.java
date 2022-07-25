package com.preview.preview.domain.web.dto.form;

import com.preview.preview.domain.form.Form;
import com.preview.preview.domain.post.Post;
import com.preview.preview.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class FormGetResponseDto {
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
