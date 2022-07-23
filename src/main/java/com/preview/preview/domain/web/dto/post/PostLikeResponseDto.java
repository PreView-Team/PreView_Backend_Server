package com.preview.preview.domain.web.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLikeResponseDto {
    private Long userId;
    private Long postId;
    private String result;
}
