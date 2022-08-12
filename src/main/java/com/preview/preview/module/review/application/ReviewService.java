package com.preview.preview.module.review.application;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.post.domain.Post;
import com.preview.preview.module.post.domain.PostRepository;
import com.preview.preview.module.review.application.dto.*;
import com.preview.preview.module.review.domain.Review;
import com.preview.preview.module.review.domain.ReviewRepository;
import com.preview.preview.module.user.domain.User;
import com.preview.preview.module.user.domain.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public ReviewCreateResponseDto createReview(long kakaoId, long postId, ReviewCreateRequestDto reviewCreateRequestDto){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Post post = postRepository.findById(postId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_POST_ID));

        if (reviewRepository.existsReviewByUserIdAndPostId(user.getId(), post.getId())){
            throw new CustomException(ErrorCode.DUPLICATE_REVIEW_RESOURCE);
        }

        Review review = Review.builder()
                .user(user)
                .post(post)
                .grade(reviewCreateRequestDto.getGrade())
                .contents(reviewCreateRequestDto.getContents()).build();

        reviewRepository.save(review);
        return ReviewCreateResponseDto.from(review);
    }

    @Transactional
    public ReviewUpdateResponseDto updateReview(long kakaoId, long reviewId, ReviewUpdateRequestDto reviewUpdateRequestDto){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Review review = reviewRepository.findReviewById(reviewId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_REVIEW_ID));

        if (review.getUser().getId() != user.getId()){
            throw new CustomException(ErrorCode.NOT_EQUAL_USER_RESOURCE);
        }

        review.setContents(reviewUpdateRequestDto.getContents());
        review.setGrade(reviewUpdateRequestDto.getGrade());
        return ReviewUpdateResponseDto.from(review);
    }

    @Transactional
    public ReviewDeleteResponseDto deleteReview(long kakaoId, long reviewId){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Review review = reviewRepository.findReviewById(reviewId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_REVIEW_ID));

        if (review.getUser().getId() != user.getId()){
            throw new CustomException(ErrorCode.NOT_EQUAL_USER_RESOURCE);
        }
        reviewRepository.delete(review);
        return ReviewDeleteResponseDto.from(review);
    }

    @Transactional
    public List<ReviewDto> getReviews(long postId, Pageable pageable){
        Post post = postRepository.findById(postId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_POST_ID));
        List<Review> list = reviewRepository.findReviewByPostIdAndDeletedDateIsNull(post.getId(), pageable).toList();
        List<ReviewDto> lists = new ArrayList<>();
        for (Review s : list){
            lists.add(ReviewDto.from(s));
        }
        return lists;
    }

}
