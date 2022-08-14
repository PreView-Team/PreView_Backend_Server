package com.preview.preview.module.user.application.dto;

import com.preview.preview.module.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserUpdateResponseDto {
    private String result;

    public static UserUpdateResponseDto from(User user){
        return UserUpdateResponseDto.builder()
                .result("성공")
                .build();
    }

}
