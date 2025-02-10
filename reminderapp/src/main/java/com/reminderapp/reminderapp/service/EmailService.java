package com.reminderapp.reminderapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendBirthdayEmail(String to, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Happy Birthday 🎉");
        message.setText("Dear " + name + ",\n\nWishing you a very Happy Birthday! 🎂🎉\n\nBest Regards,\nYour App Team");

        mailSender.send(message);
    }
}

