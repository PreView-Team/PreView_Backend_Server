package com.preview.preview.module.user.domain;

import lombok.Builder;

@Builder
public class UserSpecification {
    Long id;
    Long kakaoId;
    String nickname;
}
