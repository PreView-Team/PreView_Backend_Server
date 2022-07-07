package com.preview.preview.domain.user;

import com.preview.preview.domain.enterprise.Enterprise;
import com.preview.preview.domain.job.Job;
import com.preview.preview.domain.web.dto.enterprise.EnterpriseDto;
import com.preview.preview.domain.web.dto.job.JobDto;
import com.preview.preview.domain.web.dto.user.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 db 저장 확인")
    void save(){
        //given
        Set<Job> jobs = new HashSet<>();
        jobs.add(new Job("프로그래머"));

        Set<Enterprise> enterprises = new HashSet<>();
        enterprises.add(new Enterprise("삼성전자"));

        User user = User.builder()
                .activated(true)
                .nickname("박태순")
                .kakaoId(123241231L)
                .likedJobs(jobs)
                .email("test123@daum.net")
                .likedEnterprises(enterprises)
                .build();
        // when
        User savedUser = userRepository.save(user);
        // then
        Assertions.assertThat(savedUser).isSameAs(user);
    }
}