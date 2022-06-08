package com.preview.preview.domain.user;

import com.preview.preview.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Setter
    private String password;

    private String provider;    // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지

    private String providerId;  // oauth2를 이용할 경우 아이디값

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @Builder(builderClassName = "UserDetailRegister", builderMethodName = "userDetailRegister")
    public User(String username, String password, String email, Role role) {
        this.name = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public User(String username, String password, String email, Role role, String provider, String providerId) {
        this.name = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

}
