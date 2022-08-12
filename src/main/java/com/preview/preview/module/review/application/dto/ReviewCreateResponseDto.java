package com.preview.preview.module.review.application.dto;

import com.preview.preview.module.review.domain.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewCreateResponseDto {
    private String result;

    public static ReviewCreateResponseDto from(Review review){
        if (review == null) return null;
        return ReviewCreateResponseDto.builder().result("성공").build();
    }

}
