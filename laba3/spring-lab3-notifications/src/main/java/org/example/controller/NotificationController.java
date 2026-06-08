package org.example.controller;

import org.example.service.NotificationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationManager notificationManager;

    public NotificationController(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    @GetMapping("/notify")
public String notify(@RequestParam String message,
                     @RequestParam String email,
                     @RequestParam String type) {
    notificationManager.notify(message, email, type);
    return "Отправлено через: " + type;
}
    }