package com.preview.preview.module.review.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preview.preview.module.review.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ReviewsGetResponseDto {

    private long reviewId;
    private String nickname;
    private float grade;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime createTime;

    public static ReviewsGetResponseDto from(Review review){
        if (review == null) return null;
        return ReviewsGetResponseDto.builder()
                .reviewId(review.getId())
                .createTime(review.getCreatedDate())
                .nickname(review.getPost().getUser().getMentor().getNickname())
                .grade(review.getGrade()).build();
    }

}
