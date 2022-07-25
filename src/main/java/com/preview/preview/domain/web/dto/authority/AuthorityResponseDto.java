package com.preview.preview.domain.web.dto.authority;

import com.preview.preview.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorityResponseDto {
    public long id;
    public String result;

    public static AuthorityResponseDto from(User user){
        if (user == null) return null;

        return AuthorityResponseDto.builder()
                .id(user.getId())
                .result("멘토 처리 되었습니다.")
                .build();
        }
}

