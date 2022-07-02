package com.preview.preview.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "이미 가입된 계정입니다."),;

    private final HttpStatus httpStatus;
    private final String detail;
}

