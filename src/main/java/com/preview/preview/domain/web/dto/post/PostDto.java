package com.preview.preview.domain.web.dto.post;

import com.preview.preview.domain.category.Category;
import com.preview.preview.domain.post.Post;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.web.dto.authority.AuthorityDto;
import com.preview.preview.domain.web.dto.job.JobDto;
import com.preview.preview.domain.web.dto.user.UserDto;
import lombok.*;

import java.util.stream.Collectors;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String contents;
    private String title;
    private String subTitle;
    private Category category;
    private User user;

    public static PostDto from(Post post){
        if (post == null) return null;
        return PostDto.builder()
                .contents(post.getContent())
                .title(post.getTitle())
                .subTitle(post.getSub_title())
                .category(post.getCategory())
                .user(post.getUser())
                .build();
    }
}
