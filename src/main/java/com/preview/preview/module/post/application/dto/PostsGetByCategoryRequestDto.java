package com.preview.preview.module.post.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostsGetByCategoryRequestDto {
    private long categoryId;
    private long userKakaoId;
}
