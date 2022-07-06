package com.preview.preview.domain.service.email;

import com.preview.preview.domain.email.Email;
import com.preview.preview.domain.email.EmailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService{

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public EmailServiceImpl(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    @Override
    public ResponseEntity<Boolean> saveEmail(String name) throws MessagingException {

        // gmail 보내는 코드 작성
        MimeMessage m = mailSender.createMimeMessage();
        MimeMessageHelper h = new MimeMessageHelper(m,"UTF-8");
        String randomCode = randomCode();
        h.setFrom(from);
        h.setTo(name);
        h.setSubject("인증 번호");
        h.setText("인증 번호 : "+randomCode);
        mailSender.send(m);

        Email email = Email.builder().name(name).code(randomCode).build();
        emailRepository.save(email);
        return ResponseEntity.ok(true);
    }

    @Override
    public Boolean findEmailByEmailAndCode(String name, String code) {
        return emailRepository.existsEmailByNameAndCode(name, code);
    }

    public String randomCode(){
        return String.valueOf((int)(Math.random() * (999999 - 100000 + 1)) + 100000);
    }
}
