package com.preview.preview.module.post.application.dto;
import com.preview.preview.module.post.domain.PostLike;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostLikeResponseDto {
    private String result;

    public static PostLikeResponseDto from(PostLike postLike){
        if (postLike == null) return null;
        return PostLikeResponseDto.builder()
                .result("성공")
                .build();
    }
}
