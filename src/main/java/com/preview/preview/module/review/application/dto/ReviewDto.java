package com.preview.preview.module.review.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preview.preview.module.review.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ReviewDto {

    private String nickname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime createTime;
    private String contents;
    private float grade;

    public static ReviewDto from(Review review){
        if (review == null) return null;
        return ReviewDto.builder().contents(review.getContents())
                .createTime(review.getCreatedDate())
                .grade(review.getGrade())
                .nickname(review.getUser().getNickname())
                .build();
    }
}
