package com.preview.preview.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    DUPLICATE_SIGNUP_RESOURCE(HttpStatus.CONFLICT,"P001", "이미 가입된 계정입니다."),
    UNAUTHORIZED_KAKAO_LOGIN(HttpStatus.UNAUTHORIZED,"P002", "잘못된 토큰입니다."),
    NOT_EXISTED_USER_ID(HttpStatus.BAD_REQUEST, "P003", "가입되지 않은 계정입니다."),
    DUPLICATE_AUTHORITY_RESOURCE(HttpStatus.CONFLICT,"P004", "이미 등록된 멘토입니다."),
    NOT_EXISTED_CATEGORY_ID(HttpStatus.BAD_REQUEST, "P005", "등록되지 않은 카테고리입니다."),
    NOT_EXISTED_POST_ID(HttpStatus.BAD_REQUEST, "P006", "찾을 수 없는 게시글입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    ErrorCode(HttpStatus status, String code, String message) {
        this.httpStatus = status;
        this.message = message;
        this.code = code;
    }
}

