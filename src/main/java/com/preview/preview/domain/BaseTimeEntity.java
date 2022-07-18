package com.preview.preview.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    @CreatedDate // 생성할 때 자동저장
    private LocalDateTime createdDate;

    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    @LastModifiedDate // 수정할 때 자동저장
    private LocalDateTime modifiedDate;

    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime deletedDate;
}
