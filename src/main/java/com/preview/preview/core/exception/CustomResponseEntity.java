package com.preview.preview.core.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomResponseEntity {
    private String code;
    private String message;
    private String httpCode;
}
