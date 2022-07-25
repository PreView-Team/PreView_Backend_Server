package com.preview.preview.domain.web.dto.user;

import com.preview.preview.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignupResponseDto {
    private String result;
    private Long id;

    public static SignupResponseDto from(User user){
        if (user == null) return null;
        return SignupResponseDto.builder().result("가입 성공")
                .id(user.getId())
                .build();
    }
}
