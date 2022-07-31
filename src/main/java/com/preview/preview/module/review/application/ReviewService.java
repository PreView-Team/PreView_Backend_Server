package com.preview.preview.module.review.application;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.post.domain.Post;
import com.preview.preview.module.post.domain.PostRepository;
import com.preview.preview.module.review.application.dto.ReviewCreateRequestDto;
import com.preview.preview.module.review.application.dto.ReviewCreateResponseDto;
import com.preview.preview.module.review.domain.Review;
import com.preview.preview.module.review.domain.ReviewRepository;
import com.preview.preview.module.user.domain.User;
import com.preview.preview.module.user.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, PostRepository postRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public ReviewCreateResponseDto createReview(long kakaoId, long postId, ReviewCreateRequestDto reviewCreateRequestDto){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_POST_ID));

        Review review = Review.builder()
                .user(user)
                .post(post)
                .contents(reviewCreateRequestDto.getContents()).build();

        reviewRepository.save(review);
        return ReviewCreateResponseDto.from(review);
    }

}
