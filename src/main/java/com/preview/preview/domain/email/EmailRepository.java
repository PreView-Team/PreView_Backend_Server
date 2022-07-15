package com.preview.preview.domain.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {
    public Boolean existsEmailByNameAndCode(String name, String code);
}
