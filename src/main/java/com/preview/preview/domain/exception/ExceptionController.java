package com.preview.preview.domain.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler({
            RuntimeException.class
    })
    public ResponseEntity<Object> BadRequestException(final RuntimeException ex){
        log.warn("error", ex);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity handleAccessDeniedException(final AccessDeniedException ex){
        log.warn("error", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    // 500
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex) {
        log.info(ex.getClass().getName());
        log.error("error", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomResponseEntity> myCustomException(CustomException ex){
        CustomResponseEntity customResponseEntity = new CustomResponseEntity();

        ErrorCode errorCode = ex.getErrorCode();
        customResponseEntity.setCode(errorCode.getCode());
        customResponseEntity.setHttpCode(errorCode.getHttpStatus().toString());
        customResponseEntity.setMessage(errorCode.getMessage());

        return new ResponseEntity<>(customResponseEntity, errorCode.getHttpStatus());
    }


}
