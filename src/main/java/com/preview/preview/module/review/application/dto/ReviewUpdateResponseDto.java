package com.preview.preview.module.review.application.dto;

import com.preview.preview.module.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReviewUpdateResponseDto {
    private String result;

    public static ReviewUpdateResponseDto from(Review review){
        if (review == null) return null;
        return ReviewUpdateResponseDto.builder().result("성공").build();
    }

}
