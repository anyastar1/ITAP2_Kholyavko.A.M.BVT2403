package com.example.spring_lab3_notifications.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("customEmail")
public class EmailService implements MessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("EMAIL to " + recipient + ": " + message);
    }
}