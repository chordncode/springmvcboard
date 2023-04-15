package com.chordncode.springmvcboard.data.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

    private JavaMailSender mailSender;

    public MailUtil(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String recipient, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    
}
