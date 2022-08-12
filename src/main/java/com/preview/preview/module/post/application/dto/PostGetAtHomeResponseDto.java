package com.preview.preview.module.post.application.dto;

import com.preview.preview.module.post.domain.Post;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostGetAtHomeResponseDto {
    private long postId;
    private String nickname;
    private String categoryName;
    private String title;
    private int likeCnt;
    private int commentCnt;

    public static PostGetAtHomeResponseDto from(Post post){
        if (post==null) return null;
        return PostGetAtHomeResponseDto.builder()
                .postId(post.getId())
                .commentCnt(post.getReviewCnt())
                .nickname(post.getUser().getMentor().getNickname())
                .title(post.getTitle())
                .likeCnt(post.getPostLikes().size())
                .categoryName(post.getCategory().getName())
                .build();
    }
}
