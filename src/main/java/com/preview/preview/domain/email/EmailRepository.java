package com.preview.preview.domain.email;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
    public Boolean existsEmailByNameAndCode(String name, String code);
}
