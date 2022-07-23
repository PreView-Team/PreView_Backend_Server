package com.preview.preview.domain.post;

import com.preview.preview.domain.BaseTimeEntity;
import com.preview.preview.domain.post.Post;
import com.preview.preview.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Table(name = "post_like")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PostLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_like_to_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false, foreignKey = @ForeignKey(name = "fk_like_to_post"))
    private Post post;

}
