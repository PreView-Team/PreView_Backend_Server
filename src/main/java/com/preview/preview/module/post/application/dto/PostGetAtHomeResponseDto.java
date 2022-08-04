package com.preview.preview.module.post.application.dto;

import com.preview.preview.module.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PostGetAtHomeResponseDto {
    private String nickname;
    private String categoryName;
    private String title;
    private int likeCnt;
    private int commentCnt;

    public static PostGetAtHomeResponseDto from(Post post){
        if (post==null) return null;
        return PostGetAtHomeResponseDto.builder()
                .commentCnt(post.getReviews().size())
                .nickname(post.getUser().getNickname())
                .title(post.getTitle())
                .likeCnt(post.getPostLikes().size())
                .categoryName(post.getCategory().getName())
                .build();
    }
}
