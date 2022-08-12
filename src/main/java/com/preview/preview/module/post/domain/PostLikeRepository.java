package com.preview.preview.module.post.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsPostLikeByUserIdAndPostId(Long userId, Long PostId);
    Optional<PostLike> findPostLikeByUserIdAndPostId(Long userId, Long PostId);
}
