package com.preview.preview.domain.web.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLikeRequestDto {
    private Long userKakaoId;
    private Long postId;
}
