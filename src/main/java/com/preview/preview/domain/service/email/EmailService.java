package com.preview.preview.domain.service.email;

import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;

public interface EmailService {
    //   인증번호 저장하는 기능
    public ResponseEntity<Boolean> saveEmail(String email) throws MessagingException;
    //   인증번호 확인하는 기능
    public Boolean findEmailByEmailAndCode(String email, String code);
}
