package com.preview.preview.domain.post;

import com.preview.preview.domain.web.dto.post.PostsGetByCategoryResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByCategoryId(Long categoryId);
}
