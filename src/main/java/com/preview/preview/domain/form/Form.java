package com.preview.preview.domain.form;

import com.preview.preview.domain.BaseTimeEntity;
import com.preview.preview.domain.post.Post;
import com.preview.preview.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "form")
public class Form extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "form_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_form_to_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false, foreignKey = @ForeignKey(name = "fk_form_to_post"))
    private Post post;

    @Setter
    private String name;

    @Setter
    private String phoneNumber;

    @Setter
    private String university;

    @Setter
    private String wantedJob;

    @Setter
    private String context; // 상담 받고 싶은 내용

    private boolean status; // 상태 정보
}
