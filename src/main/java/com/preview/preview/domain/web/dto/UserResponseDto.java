package com.preview.preview.domain.web.dto;

import com.preview.preview.domain.posts.Posts;
import com.preview.preview.domain.user.Role;
import com.preview.preview.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class UserResponseDto {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String provider;    // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지

    private String providerId;  // oauth2를 이용할 경우 아이디값

    private Role role;

    public UserResponseDto(User entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.provider = entity.getProvider();
        this.providerId = entity.getProviderId();
        this.role = entity.getRole();
    }
}
