package com.example.spring_lab3_notifications.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.spring_lab3_notifications.model.dto.NotificationDto;
import com.example.spring_lab3_notifications.model.entity.Notification;
import com.example.spring_lab3_notifications.model.enums.NotificationChannel;
import com.example.spring_lab3_notifications.model.enums.NotificationStatus;
import com.example.spring_lab3_notifications.service.NotificationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/add")
    public NotificationDto createNotification(@RequestBody @Valid NotificationDto request) {
        Notification response = notificationService.createNotification(request);
        return toDto(response);
    }

    @GetMapping("/all")
    public List<NotificationDto> getAllNotifications() {
        return notificationService.getAllNotifications().stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public NotificationDto getNotificationById(@PathVariable Long id) {
        Notification response = notificationService.getNotificationById(id);
        return toDto(response);
    }

    @PutMapping("/{id}")
    public NotificationDto updateNotification(@PathVariable Long id,
                                              @RequestBody @Valid NotificationDto request) {
        Notification response = notificationService.updateNotification(id, request);
        return toDto(response);
    }

    @DeleteMapping("/{id}")
    public String deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return "Уведомление удалено";
    }

    @GetMapping("/status/{status}")
    public List<NotificationDto> getByStatus(@PathVariable NotificationStatus status) {
        return notificationService.getNotificationsByStatus(status).stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/channel/{channel}")
    public List<NotificationDto> getByChannel(@PathVariable NotificationChannel channel) {
        return notificationService.getNotificationsByChannel(channel).stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/recipient/{recipientId}")
    public List<NotificationDto> getByRecipientId(@PathVariable Long recipientId) {
        return notificationService.getNotificationsByRecipientId(recipientId).stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/status/{status}/channel/{channel}")
    public List<NotificationDto> getByStatusAndChannel(
            @PathVariable NotificationStatus status,
            @PathVariable NotificationChannel channel) {
        return notificationService.getByStatusAndChannel(status, channel).stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/status/{status}/sorted")
    public List<NotificationDto> getByStatusSorted(@PathVariable NotificationStatus status) {
        return notificationService.getByStatusOrderByCreatedAtAsc(status).stream()
                .map(this::toDto)
                .toList();
    }
    @GetMapping("/sorted/desc")
    public List<NotificationDto> getAllSortedDesc() {
        return notificationService.getAllOrderByCreatedAtDesc().stream()
                .map(this::toDto)
                .toList();
    }
    @GetMapping("/recipient/{recipientId}/status/{status}")
    public List<NotificationDto> getByRecipientIdAndStatus(
            @PathVariable Long recipientId,
            @PathVariable NotificationStatus status) {
        return notificationService.getByRecipientIdAndStatus(recipientId, status).stream()
                .map(this::toDto)
                .toList();
    }
    private NotificationDto toDto(Notification notification) {
        return NotificationDto.builder()
                .title(notification.getTitle())
                .message(notification.getMessage())
                .channel(notification.getChannel())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .sentAt(notification.getSentAt())
                .recipientId(notification.getRecipient().getId())
                .build();
    }
}