package com.preview.preview.module.post.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preview.preview.module.post.domain.Post;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostsGetByCategoryResponseDto {
    private Long postId;
    private String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    private List<String> jobList;
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
                .jobList(post.getUser().getMentor().getMentorJobList())
                .nickname(post.getUser().getMentor().getNickname())
                .title(post.getTitle())
                .like(like)
                .likeCount(post.getPostLikes().size())
                .grade(post.getGrade())
                .build();
    }



}
