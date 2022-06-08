package com.preview.preview.domain.web.dto;

import com.preview.preview.domain.posts.Posts;
import com.preview.preview.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private User user;
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, User user){
        this.user = user;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .user(user)
                .build();
    }

    public void setUser(User user){

    }
}
