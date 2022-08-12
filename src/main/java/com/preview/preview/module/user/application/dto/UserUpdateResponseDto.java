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
    private long id;

    public static UserUpdateResponseDto from(User user){
        return UserUpdateResponseDto.builder().id(user.getId())
                .result("유저 정보를 수정하였습니다.")
                .build();
    }

}
