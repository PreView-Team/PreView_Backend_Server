package com.preview.preview.domain.user;

import com.preview.preview.domain.enterprise.Enterprise;
import com.preview.preview.domain.job.Job;
import com.preview.preview.domain.web.dto.enterprise.EnterpriseDto;
import com.preview.preview.domain.web.dto.job.JobDto;
import com.preview.preview.domain.web.dto.user.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.EntityInformation;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @BeforeEach
    @DisplayName("유저 db 저장 확인")
    void save(){
        //given
        Set<Job> jobs = new HashSet<>();
        jobs.add(new Job("프로그래머"));

        User user = User.builder()
                .activated(true)
                .nickname("박태순")
                .kakaoId(123241231L)
                .likedJobs(jobs)
                .likedEnterprises(null)
                .build();

        //when
        User savedUser = userRepository.save(user);
        //then
        Assertions.assertThat(user).isSameAs(savedUser);
    }

    @Test
    @DisplayName("유저 정보 업데이트")
    void update(){
        // given
        Set<Job> jobs = new HashSet<>();
        jobs.add(new Job("선생님"));

        Set<Enterprise> enterprises = new HashSet<>();
        enterprises.add(new Enterprise("네이버"));
        enterprises.add(new Enterprise("삼성전자"));

        Optional<User> user = userRepository.findById(1L);
        // when
        user.ifPresent(selectedUser -> {
            selectedUser.setLikedEnterprises(enterprises);
            selectedUser.setLikedJobs(jobs);
            userRepository.save(selectedUser);
        });
        user = userRepository.findById(1L);
        //then
        Assertions.assertThat(user.get().likedEnterprises).isEqualTo(enterprises);
    }

}