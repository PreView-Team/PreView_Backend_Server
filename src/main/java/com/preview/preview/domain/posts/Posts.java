package com.preview.preview.domain.posts;

import com.preview.preview.domain.BaseTimeEntity;
import com.preview.preview.domain.user.User;
import com.preview.preview.global.auth.PrincipalOauth2UserService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
