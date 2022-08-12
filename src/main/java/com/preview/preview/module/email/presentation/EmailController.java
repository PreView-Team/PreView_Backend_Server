package com.preview.preview.module.email.presentation;
import com.preview.preview.module.email.application.EmailServiceImpl;
import com.preview.preview.module.email.application.dto.EmailVerifiedRequestDto;
import com.preview.preview.module.user.application.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final UserServiceImpl userService;
    private final EmailServiceImpl emailService;

    public EmailController(UserServiceImpl userService, EmailServiceImpl emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/send/{email}")
    public ResponseEntity<Boolean> sendEmail(@PathVariable String email) throws MessagingException {
        // 이미 존재하는 이메일
        if (userService.existedByEmail(email) == true) return ResponseEntity.ok(false);

        return emailService.saveEmail(email);
    }

    @PostMapping("/{email}")
    public ResponseEntity<Boolean> verifyCode(@PathVariable String email, @RequestBody EmailVerifiedRequestDto code){
        return ResponseEntity.ok(emailService.findEmailByEmailAndCode(email, code.getCode()));
    }

}
