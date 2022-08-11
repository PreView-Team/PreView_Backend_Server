package com.preview.preview.module.review.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findReviewById(long id);
    boolean existsReviewByUserIdAndPostId(long userId, long postId);
    Page<Review> findReviewByPostIdAndDeletedDateIsNull(long postId, Pageable pageable);

}
