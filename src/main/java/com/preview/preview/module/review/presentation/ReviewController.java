package com.preview.preview.module.review.presentation;

import com.preview.preview.module.review.application.ReviewService;
import com.preview.preview.module.review.application.dto.ReviewCreateRequestDto;
import com.preview.preview.module.review.application.dto.ReviewCreateResponseDto;
import com.preview.preview.module.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/api/post/{postId}/review")
    public ResponseEntity<ReviewCreateResponseDto> createReview(
            @AuthenticationPrincipal User user,
            @PathVariable Long postId,
            @RequestBody ReviewCreateRequestDto reviewCreateRequestDto){
        return ResponseEntity.ok(reviewService.createReview(user.getKakaoId(), postId, reviewCreateRequestDto));
    }

}
