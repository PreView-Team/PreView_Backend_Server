package com.preview.preview.domain.web.dto.post;

import com.preview.preview.domain.post.Post;
import com.preview.preview.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostsGetByCategoryResponseDto {
    private Long id;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String category;
    private String user;
    private String title;
    private String subTitle;
    private boolean like;


    public static PostsGetByCategoryResponseDto of (Post post, boolean like){

        return PostsGetByCategoryResponseDto.builder()
                .id(post.getId())
                .contents(post.getContent())
                .createdAt(post.getCreatedDate())
                .updatedAt(post.getModifiedDate())
                .category(post.getCategory().getName())
                .user(post.getUser().getNickname())
                .title(post.getTitle())
                .subTitle(post.getSub_title())
                .like(like)
                .build();
    }



}
