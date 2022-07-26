package com.preview.preview.module.post.infrastructure;

import com.preview.preview.module.post.domain.Post;
import com.preview.preview.module.post.domain.PostRepository;
import com.preview.preview.module.post.domain.PostSpecification;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
public class DefaultPostRepository {

    /*private final JpaPostRepository jpaRepository;

    public DefaultPostRepository(JpaPostRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<Post> findOne(PostSpecification specification) {
        return jpaRepository.findById(specification.getId());
    }

    @Override
    public Optional<Post> findPostByCategoryId(Long id) {

    }

    @Override
    public Post save(Post post) {
        System.out.println(post);
        return jpaRepository.save(post);
    }*/
}
