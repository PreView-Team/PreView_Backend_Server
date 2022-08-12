package com.preview.preview.module.review.presentation;

import com.preview.preview.module.review.application.ReviewService;
import com.preview.preview.module.review.application.dto.*;
import com.preview.preview.module.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/api/post/{postId}/review")
    public ResponseEntity<List<ReviewDto>> getReviews(
            @PathVariable Long postId,
            Pageable pageable){
        return ResponseEntity.ok(reviewService.getReviews(postId, pageable));
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/api/review/{reviewId}")
    public ResponseEntity<ReviewUpdateResponseDto> updateReview(
            @AuthenticationPrincipal User user,
            @PathVariable Long reviewId,
            @RequestBody ReviewUpdateRequestDto reviewUpdateRequestDto){
        return ResponseEntity.ok(reviewService.updateReview(user.getKakaoId(), reviewId, reviewUpdateRequestDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/api/review/{reviewId}")
    public ResponseEntity<ReviewDeleteResponseDto> deleteReview(
            @AuthenticationPrincipal User user,
            @PathVariable Long reviewId){
        return ResponseEntity.ok(reviewService.deleteReview(user.getKakaoId(), reviewId));
    }

}
