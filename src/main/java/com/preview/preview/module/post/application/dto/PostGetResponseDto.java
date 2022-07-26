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
    private long id;
    private String contents;
    private String title;
    private String subTitle;
    private String nickname;
    private String categoryName;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private boolean checkedLike;

    public static PostGetResponseDto from(Post post, boolean isCheckedLike) {
        if (post == null) return null;
        return PostGetResponseDto.builder()
                .id(post.getId())
                .contents(post.getContent())
                .title(post.getTitle())
                .subTitle(post.getSub_title())
                .nickname(post.getUser().getNickname())
                .categoryName(post.getCategory().getName())
                .createDateTime(post.getCreatedDate())
                .updateDateTime(post.getModifiedDate())
                .checkedLike(isCheckedLike)
                .build();
    }
}
