package com.preview.preview.module.post.application.dto;


import com.preview.preview.core.exception.CustomException;

public class PostCreateRequestDto {
    private String contents;
    private String title;

    public String getContents(){
        if (contents == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return contents;
    }

    public String getTitle(){
        if (title == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return title;
    }

}
