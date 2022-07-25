package com.preview.preview.domain.web.dto.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private long postId;
    private String title;
    private String subTitle;
    private String contents;
}
