package com.preview.preview.module.post.application.dto;

import com.preview.preview.module.post.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostUpdateResponseDto {
    private Long id;
    private String result;

    public static PostUpdateResponseDto from (Post post){
        if (post == null) return null;
        return PostUpdateResponseDto.builder()
                .id(post.getId())
                .result("수정되었습니다.").build();
    }

}
