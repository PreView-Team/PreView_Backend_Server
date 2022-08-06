package com.preview.preview.module.form.domain;

import com.preview.preview.module.job.domain.Job;
import com.preview.preview.module.post.domain.Post;
import com.preview.preview.module.user.domain.User;
import com.preview.preview.util.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    private User user; // 멘티

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false, foreignKey = @ForeignKey(name = "fk_form_to_post"))
    private Post post;

    @Setter
    private String mentorNicname;

    @Setter
    private String name;

    @Setter
    private String local; // 멘티 사는 지역

    @Setter
    private String phoneNumber;

    @Setter
    private String content; // 상담 받고 싶은 내용

    @Setter
    private String status; // 상태 정보

    private String fcmToken;

    @Setter
    private String likeJobs;

    public void setDeleteTime(){
        this.deletedDate =  LocalDateTime.now();
    }


}
