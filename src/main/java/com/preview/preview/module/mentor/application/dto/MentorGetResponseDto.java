package com.preview.preview.module.mentor.application.dto;

import com.preview.preview.module.mentor.domain.Mentor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MentorGetResponseDto{
    private String nickname; // 멘토 닉네임
    private String contents; // 멘토 소개
    private List<String> jobNames;

    public static MentorGetResponseDto from(Mentor mentor){
        if (mentor == null) return null;
        return MentorGetResponseDto.builder().contents(mentor.getContents())
                .nickname(mentor.getNickname())
                .jobNames(mentor.getMentorJobList()).build();
    }
}
