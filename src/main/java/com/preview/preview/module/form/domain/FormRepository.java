package com.preview.preview.module.form.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    boolean existsFormByUserIdAndPostId(Long userId, Long PostId);
    List<Form> findFormByuserId(Long userId);
}
