package com.example.spring_lab3_notifications.service;

import com.example.spring_lab3_notifications.model.dto.NotificationDto;
import com.example.spring_lab3_notifications.model.entity.Notification;
import com.example.spring_lab3_notifications.model.entity.User;
import com.example.spring_lab3_notifications.model.enums.NotificationChannel;
import com.example.spring_lab3_notifications.repository.NotificationRepository;
import com.example.spring_lab3_notifications.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private NotificationService notificationService;

    private User user;
    private Notification notification;
    private NotificationDto notificationDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Иван");

        notification = new Notification();
        notification.setId(1L);
        notification.setTitle("Тест");
        notification.setMessage("Сообщение");

        notificationDto = NotificationDto.builder()
                .title("Тест")
                .message("Сообщение")
                .channel(NotificationChannel.EMAIL)
                .recipientId(1L)
                .build();
    }

    // ========== ЗАДАНИЕ 3: Тест для getNotificationById() ==========
    @Test
    void getNotificationById_WhenExists_ShouldReturnNotification() {
        // given
        Long notifId = 1L;
        when(notificationRepository.findById(notifId)).thenReturn(Optional.of(notification));

        // when
        Notification result = notificationService.getNotificationById(notifId);

        // then
        assertNotNull(result);
        assertEquals(notifId, result.getId());
        assertEquals("Тест", result.getTitle());
        assertEquals("Сообщение", result.getMessage());
        verify(notificationRepository, times(1)).findById(notifId);
    }

    // ========== ЗАДАНИЕ 4: Негативный тест (уведомление не найдено) ==========
    @Test
    void getNotificationById_WhenNotFound_ShouldThrowException() {
        // given
        Long notifId = 999L;
        when(notificationRepository.findById(notifId)).thenReturn(Optional.empty());

        // when & then
        assertThrows(RuntimeException.class, () -> notificationService.getNotificationById(notifId));
        verify(notificationRepository, times(1)).findById(notifId);
    }

    // ========== Дополнительный тест для createNotification ==========
    @Test
    void createNotification_ShouldSaveAndReturnNotification() {
        // given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        // when
        Notification result = notificationService.createNotification(notificationDto);

        // then
        assertNotNull(result);
        assertEquals("Тест", result.getTitle());
    }

    @Test
    void createNotification_WhenUserNotFound_ShouldThrowException() {
        // given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // when & then
        assertThrows(RuntimeException.class, () -> notificationService.createNotification(notificationDto));
    }
}