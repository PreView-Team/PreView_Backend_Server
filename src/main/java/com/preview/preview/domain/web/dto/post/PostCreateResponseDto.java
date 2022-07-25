package com.preview.preview.domain.web.dto.post;

import com.preview.preview.domain.post.Post;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateResponseDto {

    private Long id;
    private String result;

    public static PostCreateResponseDto from(Post post){
        if (post == null) return null;
        return PostCreateResponseDto.builder()
                .id(post.getId())
                .result("성공")
                .build();
    }
}
