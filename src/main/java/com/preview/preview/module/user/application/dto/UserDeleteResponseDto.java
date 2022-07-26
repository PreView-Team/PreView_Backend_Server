package com.preview.preview.module.user.application.dto;

import com.preview.preview.module.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDeleteResponseDto {
    private long id;
    private String result;

    public static UserDeleteResponseDto from(User user){
        if (user == null) return null;
        
        return UserDeleteResponseDto.builder()
                .id(user.getId())
                .result("삭제되었습니다.")
                .build();
    }
}
