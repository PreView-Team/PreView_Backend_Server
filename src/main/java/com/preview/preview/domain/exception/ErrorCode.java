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
    NOT_EQUAL_USER_RESOURCE(HttpStatus.FORBIDDEN, "P007", "게시물을 권한이 없는 계정입니다."),
    NOT_DELETE_POST_RESOURCE(HttpStatus.BAD_REQUEST, "P008", "게시글을 지울 수 없습니다."),
    NOT_DELETE_USER_RESOURCE(HttpStatus.BAD_REQUEST, "P009", "유저를 삭제할 수 없습니다."),
    DELETE_USER_RESOURCE(HttpStatus.BAD_REQUEST, "P010", "이미 삭제된 계정입니다. 일주일 뒤에 다시 가입 바랍니다."),
    NOT_EXISTED_LIKE_JOB(HttpStatus.BAD_REQUEST, "P011","데이터 베이스에 해당 직무가 존재하지 않습니다."),
    DUPLICATE_POST_LIKE_RESOURCE(HttpStatus.CONFLICT,"P012", "이미 좋아요를 한 게시물 입니다."),
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

