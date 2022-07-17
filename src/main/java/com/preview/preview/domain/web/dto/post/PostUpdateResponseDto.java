package com.preview.preview.domain.web.dto.post;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateResponseDto {
    private Long id;
    private String result;
}
