package com.preview.preview.domain.web.dto.post;

import com.preview.preview.domain.post.Post;
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


    public static PostsGetByCategoryResponseDto of (Post post){
        return PostsGetByCategoryResponseDto.builder()
                .id(post.getId())
                .contents(post.getContent())
                .createdAt(post.getCreatedDate())
                .updatedAt(post.getModifiedDate())
                .category(post.getCategory().getName())
                .user(post.getUser().getNickname())
                .title(post.getTitle())
                .subTitle(post.getSub_title())
                .build();
    }

    public static List<PostsGetByCategoryResponseDto> of(List<Post> posts) {
        return posts.stream().map(PostsGetByCategoryResponseDto::of).collect(Collectors.toList());
    }

}
