package com.preview.preview.module.review.application.dto;

import com.preview.preview.module.review.domain.Review;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewUserGetResponseDto {
    private String nickname;
    private float grade;
    private String contents;

    public static ReviewUserGetResponseDto from(Review review){
        if (review == null) return null;
        return ReviewUserGetResponseDto.builder()
                .contents(review.getContents())
                .grade(review.getGrade())
                .nickname(review.getPost().getUser().getMentor().getNickname()).build();
    }

}
