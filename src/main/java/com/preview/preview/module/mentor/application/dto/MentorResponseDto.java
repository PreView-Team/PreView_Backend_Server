package com.preview.preview.module.mentor.application.dto;

import com.preview.preview.module.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MentorResponseDto {
    public long id;
    public String result;

    public static MentorResponseDto from(User user){
        if (user == null) return null;

        return MentorResponseDto.builder()
                .id(user.getId())
                .result("멘토 처리 되었습니다.")
                .build();
        }
}

