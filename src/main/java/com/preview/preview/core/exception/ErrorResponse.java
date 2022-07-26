package com.preview.preview.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private HttpStatus status;
    private String code;

    public ErrorResponse(ErrorCode code){
        this.message = code.getMessage();
        this.status = code.getHttpStatus();
        this.code = code.getCode();
    }
}
