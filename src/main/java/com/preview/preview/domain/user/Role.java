package com.preview.preview.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ROLE_USER, ROLE_MANAGER, ROLE_ADMIN
}
