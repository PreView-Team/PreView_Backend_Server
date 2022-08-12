package com.preview.preview.module.review.application.dto;


import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;


public class ReviewUpdateRequestDto {
    private String contents;
    private Integer grade;

    public String getContents() {
        if (contents == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return contents;
    }

    public Integer getGrade() {
        if (grade == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return grade;
    }
}
