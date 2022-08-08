package com.preview.preview.module.mentor.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findMentorById(Long mentorId);
    Boolean existsMentorByNickname(String name);

}
