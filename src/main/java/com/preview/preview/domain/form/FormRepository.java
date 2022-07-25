package com.preview.preview.domain.form;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
    boolean existsFormByUserIdAndPostId(Long userId, Long PostId);
}
