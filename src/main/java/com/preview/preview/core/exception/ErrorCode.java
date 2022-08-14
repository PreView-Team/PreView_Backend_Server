package com.preview.preview.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    DUPLICATE_SIGNUP_RESOURCE(HttpStatus.CONFLICT,"P001", "이미 가입된 계정입니다."),
    UNAUTHORIZED_KAKAO_LOGIN(HttpStatus.UNAUTHORIZED,"P002", "잘못된 토큰입니다."),

    NOT_EXISTED_USER_ID(HttpStatus.BAD_REQUEST, "P003", "가입되지 않은 계정입니다."),
    DUPLICATE_AUTHORITY_RESOURCE(HttpStatus.CONFLICT,"P004", "이미 등록된 멘토입니다."),
    NOT_EXISTED_CATEGORY_ID(HttpStatus.BAD_REQUEST, "P005", "등록되지 않은 카테고리입니다."),
    NOT_EXISTED_POST_ID(HttpStatus.BAD_REQUEST, "P006", "찾을 수 없는 게시글입니다."),
    NOT_EQUAL_USER_RESOURCE(HttpStatus.FORBIDDEN, "P007", "권한이 없는 계정입니다."),
    NOT_DELETE_POST_RESOURCE(HttpStatus.BAD_REQUEST, "P008", "게시글을 지울 수 없습니다."),
    NOT_DELETE_USER_RESOURCE(HttpStatus.BAD_REQUEST, "P009", "유저를 삭제할 수 없습니다."),
    DELETE_USER_RESOURCE(HttpStatus.NOT_FOUND, "P010", "이미 삭제된 계정입니다. 일주일 뒤에 다시 가입 바랍니다."),
    NOT_EXISTED_LIKE_JOB(HttpStatus.BAD_REQUEST, "P011","데이터 베이스에 해당 직무가 존재하지 않습니다."),
    DUPLICATE_POST_LIKE_RESOURCE(HttpStatus.CONFLICT,"P012", "이미 좋아요를 한 게시물 입니다."),
    DUPLICATE_POST_UNLIKE_RESOURCE(HttpStatus.CONFLICT,"P013", "이미 좋아요가 안된 게시물 입니다."),
    DUPLICATE_FORM_RESOURCE(HttpStatus.CONFLICT,"P014", "이미 신청된 제안서입니다."),
    NOT_EXISTED_FORM_ID(HttpStatus.BAD_REQUEST, "P015", "등록되지 않은 제안서입니다."),
    NOT_EQUAL_FORM_RESOURCE(HttpStatus.FORBIDDEN, "P016", "수정할 권한이 없는 계정입니다."),
    NOT_EXISTED_REVIEW_ID(HttpStatus.FORBIDDEN, "P017", "존재하지 않는 리뷰입니다."),
    DUPLICATE_REVIEW_RESOURCE(HttpStatus.CONFLICT,"P018", "이미 등록이 된 리뷰입니다."),
    NOT_EXISTED_MENTOR_ID(HttpStatus.BAD_REQUEST, "P019", "등록되지 않은 멘토입니다."),

    INVALIDED_VALUE(HttpStatus.BAD_REQUEST, "PO20", "요청 변수 값이 비어 있습니다."),

    NOT_EXISTED_MENTOR_JOB(HttpStatus.BAD_REQUEST, "PO21", "멘토 관심 직무 등록해주세요."),

    UNAUTHORIZED_KAKAO_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED,"P022", "잘못된 리프레시 토큰 입니다."),

    DUPLICATE_NICKNAME_RESOURCE(HttpStatus.CONFLICT,"P023", "이미 중복된 닉네임입니다."),
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

