package com.preview.preview.module.mentor.application.dto;

import com.preview.preview.module.mentor.domain.Mentor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MentorUpdateResponseDto {
    private String result;

    public static MentorUpdateResponseDto from(Mentor mentor){
        if (mentor == null) return null;
        return MentorUpdateResponseDto.builder().result("멘토 업데이트 성공").build();
    }
}
