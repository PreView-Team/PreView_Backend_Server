package com.preview.preview.domain.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    User findByName(String name);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByName(String username);
}
