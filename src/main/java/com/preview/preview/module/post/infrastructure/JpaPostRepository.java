package com.preview.preview.module.post.infrastructure;

import com.preview.preview.module.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<Post, Long> {

}
