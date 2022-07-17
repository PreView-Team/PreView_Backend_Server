package com.preview.preview.domain.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(Long id);

    Boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    Optional<User> findByKakaoId(Long id);

    // Spring Security
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByNickname(String nickname);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByKakaoId(Long kakaoId);



}
