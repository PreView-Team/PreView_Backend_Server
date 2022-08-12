package com.preview.preview.module.form.application.dto;

import com.preview.preview.core.exception.CustomException;


public class FormCreateRequestDto {
    private long postId;
    private String name;
    private String phoneNumber;
    private String contents;
    private String local;
    private String fcmToken;
    private String jobNames;

    public long getPostId() {
        if (postId == 0) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return postId;
    }

    public String getName() {
        if (name == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return name;
    }

    public String getPhoneNumber() {
        if (phoneNumber == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return phoneNumber;
    }

    public String getContents() {
        if (contents == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return contents;
    }

    public String getLocal() {
        if (local == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return local;
    }

    public String getFcmToken() {
        if (fcmToken == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return fcmToken;
    }

    public String getJobNames() {
        if (jobNames == null) throw new CustomException(ErrorCode.INVALIDED_VALUE);
        return jobNames;
    }
}
