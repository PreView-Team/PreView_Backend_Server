package com.preview.preview.module.post.application.dto;

import com.preview.preview.module.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostGetByMentorResponseDto {
    private String title;
    private String contents;

    public static PostGetByMentorResponseDto from(Post post){
        if (post == null) return null;
        return PostGetByMentorResponseDto.builder().title(post.getTitle()).contents(post.getContent()).build();
    }
}
