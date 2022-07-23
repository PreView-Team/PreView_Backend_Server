package com.preview.preview.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsPostLikeByUserIdAndPostId(Long userId, Long PostId);

    Optional<PostLike> findPostLikeByUserIdAndPostId(Long userId, Long PostId);

}
