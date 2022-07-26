package com.preview.preview.module.post.application.dto;
import com.preview.preview.module.post.domain.Post;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateResponseDto {

    private String result;

    public static PostCreateResponseDto from(Post post){
        if (post == null) return null;
        return PostCreateResponseDto.builder()
                .result("성공")
                .build();
    }
}
