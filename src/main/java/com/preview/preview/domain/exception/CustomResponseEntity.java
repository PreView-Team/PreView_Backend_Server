package com.preview.preview.domain.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.criteria.CriteriaBuilder;

@NoArgsConstructor
@Getter
@Setter
public class CustomResponseEntity {
    private String code;
    private String message;
    private String httpCode;
}
