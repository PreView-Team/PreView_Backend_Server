package com.preview.preview.module.post.application.dto;

import com.preview.preview.module.post.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostDeleteResponseDto {
    long id;
    String result;

    public static PostDeleteResponseDto from(Post post){
        if (post == null) return null;
        return PostDeleteResponseDto.builder()
                .id(post.getId())
                .result("게시글을 삭제하였습니다.")
                .build();
    }
}
