package com.preview.preview.module.post.domain;

import com.preview.preview.core.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/*public interface PostRepository extends Repository<Post, PostSpecification> {
    Optional<Post> findPostByCategoryId(Long id);
    Post save(Post post);
}*/

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByCategoryName(String name);
}
