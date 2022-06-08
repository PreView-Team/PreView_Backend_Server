package com.preview.preview.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void save(){

        User user = User.oauth2Register()
                .username("박태순")
                .email("xotns0518@naver.com")
                .password("123")
                .provider("kakao")
                .providerId("1a2b")
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        System.out.print(user.getId()+"하이하이");
        assertThat(userRepository.findByName("박태순").getName()).isEqualTo(user.getName());
    }
}