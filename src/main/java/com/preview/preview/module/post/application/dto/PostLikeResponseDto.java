package com.preview.preview.module.post.application.dto;
import com.preview.preview.module.post.domain.PostLike;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostLikeResponseDto {
    private Long userId;
    private Long postId;
    private String result;

    public static PostLikeResponseDto from(PostLike postLike){
        if (postLike == null) return null;
        return PostLikeResponseDto.builder()
                .userId(postLike.getUser().getId())
                .postId(postLike.getPost().getId())
                .result("좋아요 등록 성공")
                .build();
    }
}
