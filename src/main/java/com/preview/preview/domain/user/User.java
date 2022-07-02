package com.preview.preview.domain.user;

import com.preview.preview.domain.BaseTimeEntity;
import com.preview.preview.domain.authority.Authority;
import com.preview.preview.domain.likedjob.LikedJob;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = true, name = "nickname")
    private String nickname; // 닉네임

    @Column(nullable = true, name = "email")
    private String email; // 이메일

    @Column(nullable = false, name = "kakao_id")
    private Long kakaoId;

    @Setter
    @Column(name = "password")
    private String password; // 비밀번호

    //private String provider;    // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지

    //private String providerId;  // oauth2를 이용할 경우 아이디값

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "user")
    Set<LikedJob> likedJobs;


}
