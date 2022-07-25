package com.preview.preview.domain.web.dto.form;

import com.preview.preview.domain.form.Form;
import com.preview.preview.domain.post.Post;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.web.dto.post.PostDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormDto {

    private User user;
    private Post post;
    private String name;
    private String phoneNumber;
    private String university;
    private String wantedJob;
    private String context; // 상담 받고 싶은 내용

    public static FormDto from(Form form){
        if (form == null) return null;
        return FormDto.builder()
                .user(form.getUser())
                .post(form.getPost())
                .name(form.getName())
                .phoneNumber(form.getPhoneNumber())
                .university(form.getUniversity())
                .wantedJob(form.getWantedJob())
                .context(form.getContext()).build();
    }
}
