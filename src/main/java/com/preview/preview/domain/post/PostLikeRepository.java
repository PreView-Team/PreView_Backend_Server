package com.preview.preview.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsPostLikeByUserIdAndPostId(Long userId, Long PostId);
}
