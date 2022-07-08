package com.preview.preview.domain.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    // Spring Security
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByNickname(String nickname);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByKakaoId(Long kakaoId);
}
