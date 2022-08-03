package com.preview.preview.module.post.application.dto;
import com.preview.preview.module.post.domain.Post;
import com.preview.preview.module.review.application.dto.ReviewDto;
import com.preview.preview.module.review.domain.Review;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostGetResponseDto {
    private long postId;
    private String contents;
    private String title;
    private String nickname;
    private List<String> jobList;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private String introduce;
    private List<ReviewDto> reviews;
    private double grade;

    public static PostGetResponseDto from(Post post) {
        if (post == null) return null;

        List<ReviewDto> list = new ArrayList<>();
        int cnt=0;
        for (Review review: post.getReviews()){
            cnt++;
            list.add(ReviewDto.from(review));
            if (cnt == 10) break;
        }

        return PostGetResponseDto.builder()
                .postId(post.getId())
                .contents(post.getContent())
                .title(post.getTitle())
                .nickname(post.getUser().getMentor().getNickname())
                .jobList(post.getUser().getMentor().getMentorJobList())
                .createDateTime(post.getCreatedDate())
                .updateDateTime(post.getModifiedDate())
                .introduce(post.getUser().getMentor().getContents())
                .grade(post.getGrade())
                .reviews(list)
                .build();
    }
}
