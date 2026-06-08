package org.example.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {
    private final Map<String, MessageService> messageServices;

    @Autowired
    public NotificationManager(Map<String, MessageService> messageServices) {
        this.messageServices = messageServices;
    }

    public void notify(String message, String recipient, String type) {
        MessageService service = messageServices.get(type);
        if (service != null) {
            service.sendMessage(message, recipient);
        } else {
            System.out.println("Сервис не найден: " + type);
        }
    }
}