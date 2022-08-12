package com.preview.preview.module.review.application.dto;

import com.preview.preview.module.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReviewDeleteResponseDto {
    private String result;

    public static ReviewDeleteResponseDto from(Review review){
        if (review == null) return null;
        return ReviewDeleteResponseDto.builder().result("리뷰 삭제 성공").build();
    }

}
