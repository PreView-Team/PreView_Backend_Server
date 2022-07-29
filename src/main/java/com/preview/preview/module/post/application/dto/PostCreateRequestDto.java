package com.preview.preview.module.post.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequestDto {
    private String contents;
    private String title;
    private String categoryName;
}
