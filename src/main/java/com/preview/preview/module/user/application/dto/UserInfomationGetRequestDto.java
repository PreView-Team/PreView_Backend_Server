package com.preview.preview.module.user.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.preview.preview.module.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserInfomationGetRequestDto {
    private String nickname;
    @JsonProperty("isMentored")
    private boolean isMentored;
    private List<String> jobNames;

    public static UserInfomationGetRequestDto from(User user){
        if(user == null) return null;
        return UserInfomationGetRequestDto.builder()
                .nickname(user.getNickname())
                .isMentored(user.isMentored())
                .jobNames(user.getLikedJobsInProfile())
                .build();
    }
}
