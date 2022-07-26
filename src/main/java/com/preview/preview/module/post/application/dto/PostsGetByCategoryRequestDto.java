package com.preview.preview.module.post.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PostsGetByCategoryRequestDto {
    private String categoryName;
}
