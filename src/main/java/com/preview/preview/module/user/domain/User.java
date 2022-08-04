package com.preview.preview.module.user.domain;

import com.preview.preview.module.auth.domain.Authority;
import com.preview.preview.module.enterprise.domain.Enterprise;
import com.preview.preview.module.form.domain.Form;
import com.preview.preview.module.form.domain.MentorForm;
import com.preview.preview.module.job.domain.Job;
import com.preview.preview.module.mentor.domain.Mentor;
import com.preview.preview.module.review.domain.Review;
import com.preview.preview.util.BaseTimeEntity;
import io.jsonwebtoken.Claims;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Setter
    @Column(nullable = false, name = "nickname")
    private String nickname; // 닉네임

    @Column(nullable = true, name = "email")
    private String email; // 이메일

    @Column(nullable = false, name = "kakao_id")
    private Long kakaoId;

    @Setter
    @Column(name = "password")
    private String password; // 비밀번호

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "user_job",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_name", referencedColumnName = "job_name")}
    )
    private Set<Job> likedJobs;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "user_enterprise",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "enterprise_name", referencedColumnName = "enterprise_name")}
    )
    Set<Enterprise> likedEnterprises;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<Form> forms = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<MentorForm> mentorForms = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<Review> reviews = new ArrayList<>();

    @Setter
    @OneToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    public Long deleteUser(){
        deletedDate = LocalDateTime.now();
        return id;
    }

    public User(Claims claims) {
        this.kakaoId = Long.valueOf(claims.getSubject());
    }

    public boolean isExistedJob(String name){
        Iterator<Job> iterator = likedJobs.iterator();

        while (iterator.hasNext()){
            Job job = iterator.next();
            if (job.getName().equals(name)) return true;
        }
        return false;
    }

    public boolean isMentored(){
        Iterator<Authority> iterator = authorities.iterator();

        while (iterator.hasNext()){
            if (iterator.next().getAuthorityName().equals("ROLE_ADMIN")) return true;
        }
        return false;
    }

    public void setUserNickname(String nickname){
        this.nickname = nickname;
    }

    public List<String> getLikedJobsInProfile(){
        Iterator<Job> iterator = likedJobs.iterator();
        List<String> list = new ArrayList<>();

        while (iterator.hasNext()){
            Job job = iterator.next();
            list.add(job.getName());
        }

        return list;
    }




    /*
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<Post> posts = new ArrayList<>();
    */

    /*
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<PostLike> postLikes = new ArrayList<>();
    */

}
