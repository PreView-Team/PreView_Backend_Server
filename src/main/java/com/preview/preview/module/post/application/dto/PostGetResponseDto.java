package com.preview.preview.module.post.application.dto;
import com.preview.preview.module.post.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostGetResponseDto {
    private long postId;
    private String contents;
    private String title;
    private String nickname;
    private String categoryName;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private String history;
    private String introduce;

    public static PostGetResponseDto from(Post post) {
        if (post == null) return null;
        return PostGetResponseDto.builder()
                .postId(post.getId())
                .contents(post.getContent())
                .title(post.getTitle())
                .nickname(post.getUser().getNickname())
                .categoryName(post.getCategory().getName())
                .createDateTime(post.getCreatedDate())
                .updateDateTime(post.getModifiedDate())
                .history("아직 x")
                .introduce("아직 x")
                .build();
    }
}
