package com.preview.preview.module.user.domain;

import com.preview.preview.core.Repository;

import java.util.Optional;

public interface SubUserRepository extends Repository<User, UserSpecification> {
    @Override
    Optional<User> findById(Long id);

    Optional<User> findOne(UserSpecification specification);
}
