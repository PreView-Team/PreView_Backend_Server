package com.preview.preview.domain.form;

import com.preview.preview.domain.post.Post;
import com.preview.preview.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "form")
public class Form {

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

    private String name;

    private String phoneNumber;

    private String university;

    private String wantedJob;

    private String context; // 상담 받고 싶은 내용
}
