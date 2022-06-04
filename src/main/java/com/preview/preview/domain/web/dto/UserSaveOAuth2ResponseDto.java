package com.preview.preview.domain.web.dto;

import com.preview.preview.domain.posts.Posts;
import com.preview.preview.domain.user.Role;
import com.preview.preview.domain.user.User;
import lombok.Builder;

public class UserSaveOAuth2ResponseDto {
    
    private String name;
    
    private String email;
    
    private String password;

    private String provider;    // oauth2를 이용할 경우 어떤 플랫폼을 이용하는지

    private String providerId;  // oauth2를 이용할 경우 아이디값
    
    private Role role;

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public UserSaveOAuth2ResponseDto(String username, String password, String email, Role role, String provider, String providerId) {
        this.name = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public User toEntity(){
        return User.builder()
                .username(name)
                .content(content)
                .author(author)
                .build();
    }


}
