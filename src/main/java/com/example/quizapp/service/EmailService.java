package com.example.quizapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Component
@RequiredArgsConstructor
@RequestMapping("/auth/sign")
public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    public void sendEmailVerificationCode(String email, UUID emailCode) {
        SimpleMailMessage mailMessage
                = new SimpleMailMessage();


        mailMessage.setFrom(sender);
        mailMessage.setTo(email);
        mailMessage.setText("http://localhost:82/api/auth/verification-email?code="+emailCode);
        mailMessage.setSubject("this mail automatically generated!!!");
        javaMailSender.send(mailMessage);

    }
}
