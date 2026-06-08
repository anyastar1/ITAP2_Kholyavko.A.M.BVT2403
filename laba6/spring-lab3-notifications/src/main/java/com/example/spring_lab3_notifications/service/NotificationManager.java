package com.example.spring_lab3_notifications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {
    private final MessageService messageService;

    @Autowired
    public NotificationManager(@Qualifier("customEmail") MessageService messageService) {
        this.messageService = messageService;
    }

    public void notify(String message, String recipient) {
        messageService.sendMessage(message, recipient);
    }
}