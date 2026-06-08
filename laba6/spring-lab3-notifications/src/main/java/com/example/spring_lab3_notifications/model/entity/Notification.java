package com.example.spring_lab3_notifications.model.entity;
import jakarta.persistence.*;
import lombok.*;
import com.example.spring_lab3_notifications.model.enums.NotificationChannel;
import com.example.spring_lab3_notifications.model.enums.NotificationStatus;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationChannel channel;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime sentAt;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
}
