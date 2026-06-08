package com.example.spring_lab3_notifications.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import com.example.spring_lab3_notifications.model.enums.NotificationChannel;
import com.example.spring_lab3_notifications.model.enums.NotificationStatus;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    @NotBlank(message = "Заголовок не может быть пустым")
    @Size(max = 255, message = "Заголовок не должен превышать 255 символов")
    private String title;

    @NotBlank(message = "Сообщение не может быть пустым")
    private String message;

    @NotNull(message = "Канал уведомления обязателен")
    private NotificationChannel channel;

    private NotificationStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime sentAt;

    @NotNull(message = "ID получателя обязателен")
    @Positive(message = "ID получателя должен быть положительным числом")
    private Long recipientId;
}