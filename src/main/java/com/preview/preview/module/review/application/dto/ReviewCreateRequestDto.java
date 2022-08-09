package com.preview.preview.module.review.application.dto;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import lombok.*;


public class ReviewCreateRequestDto {
    private String contents;
    private float grade;

    public String getContents() {
        if (contents == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return contents;
    }

    public float getGrade() {
        if (grade == 0) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return grade;
    }
}