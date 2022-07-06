package com.preview.preview.domain.service.email;

import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;

public interface EmailService {
    public ResponseEntity<Boolean> saveEmail(String email) throws MessagingException;

    public Boolean findEmailByEmailAndCode(String email, String code);
}
