package com.preview.preview.domain.web.dto.post;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostGetResponseDto {
    private String contents;
    private String title;
    private String subTitle;
    private String nickname;
    private String categoryName;
}
