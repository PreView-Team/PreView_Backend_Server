package com.preview.preview.domain.web.dto.post;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequestDto {
    private String contents;
    private String title;
    private String subTitle;
    private long categoryId;
    private long kakaoId;
}
