package com.preview.preview.module.review.application.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewUpdateRequestDto {
    private String contents;
    private Integer grade;
}
