package com.preview.preview.module.post.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.preview.preview.module.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostsGetByMentorIdResponseDto {
    private long postId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime createTime;
    private String title;

    public static PostsGetByMentorIdResponseDto from(Post post){
        if (post == null) return null;
        return PostsGetByMentorIdResponseDto.builder()
                .createTime(post.getCreatedDate())
                .title(post.getTitle())
                .postId(post.getId()).build();
    }

}
