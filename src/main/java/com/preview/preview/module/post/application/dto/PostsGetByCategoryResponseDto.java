package com.preview.preview.module.post.application.dto;

import com.preview.preview.module.post.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostsGetByCategoryResponseDto {
    private Long postId;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String category;
    private String user;
    private String title;
    private boolean like;
    private Integer likeCount;


    public static PostsGetByCategoryResponseDto from (Post post, boolean like){
        return PostsGetByCategoryResponseDto.builder()
                .postId(post.getId())
                .contents(post.getContent())
                .createdAt(post.getCreatedDate())
                .updatedAt(post.getModifiedDate())
                .category(post.getCategory().getName())
                .user(post.getUser().getNickname())
                .title(post.getTitle())
                .like(like)
                .likeCount(post.getPostLikes().size())
                .build();
    }



}
