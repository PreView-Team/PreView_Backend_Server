package com.preview.preview.domain.posts;

import com.preview.preview.domain.user.Role;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.user.UserRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;
    @Autowired
    UserRepository userRepository;

    @After("전체 지우기")
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        User user = User.oauth2Register()
                .username("박태순")
                .email("xotns0518@naver.com")
                .password("123")
                .provider("kakao")
                .providerId("1a2b")
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        postsRepository.save(Posts.builder()
                        .id(1L)
                        .user(user)
                        .title(title)
                        .content(content)
                        .author(user.getEmail())
                        .build());

        //when
        Posts posts1 = postsRepository.findByTitle(title);

        //then
        //Posts posts = postsList.get(0);

        assertThat(posts1.getTitle()).isEqualTo(title);
        assertThat(posts1.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6,4,0,0,0);
        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.printf(">>>>>>>>> createDate="+posts.getCreatedDate()+"modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}