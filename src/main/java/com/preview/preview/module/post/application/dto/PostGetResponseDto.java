package com.preview.preview.module.post.application.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime createDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime updateDateTime;
    private String introduce;
    private List<ReviewDto> reviews;
    private double grade;

    public static PostGetResponseDto from(Post post) {
        if (post == null) return null;

        List<ReviewDto> list = new ArrayList<>();
        int cnt=0;
        int size = post.getReviews().size();

        for (Review review: post.getReviews()){
            list.add(ReviewDto.from(review));
            cnt++;
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
