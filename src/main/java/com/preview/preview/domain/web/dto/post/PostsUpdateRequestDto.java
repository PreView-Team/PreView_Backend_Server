package com.preview.preview.domain.web.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private long postId;
    private long kakaoId;
    private String title;
    private String subTitle;
    private String contents;
}
