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
    private String category;
    private String nickname; // 맨토 닉네임
    private String title; // 한 줄 소개
    private boolean like; // 좋아요 여부
    private Integer likeCount; // 좋아요 횟수
    private double grade; // 리뷰 총점


    public static PostsGetByCategoryResponseDto from (Post post, boolean like){
        return PostsGetByCategoryResponseDto.builder()
                .postId(post.getId())
                .contents(post.getContent())
                .createdAt(post.getCreatedDate())
                .category(post.getCategory().getName())
                .nickname(post.getUser().getMentor().getNickname())
                .title(post.getTitle())
                .like(like)
                .likeCount(post.getPostLikes().size())
                .grade(post.getGrade())
                .build();
    }



}
