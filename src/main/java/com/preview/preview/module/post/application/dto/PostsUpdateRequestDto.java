package com.preview.preview.module.post.application.dto;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;


public class PostsUpdateRequestDto {
    private long postId;
    private String title;
    private String contents;

    public long getPostId(){
        if (postId == 0) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return postId;
    }

    public String getTitle(){
        if (title == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return title;
    }

    public String getContents(){
        if (contents == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return contents;
    }
}
