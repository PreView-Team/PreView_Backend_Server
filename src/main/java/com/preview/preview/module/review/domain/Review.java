package com.preview.preview.module.review.domain;

import com.preview.preview.module.post.domain.Post;
import com.preview.preview.module.user.domain.User;
import com.preview.preview.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Setter
    @Getter
    @Column(name = "contents")
    private String contents;

    @Setter
    @Getter
    @Column(name = "grade")
    private float grade; // 평점

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_like_to_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false, foreignKey = @ForeignKey(name = "fk_like_to_post"))
    private Post post;



}
