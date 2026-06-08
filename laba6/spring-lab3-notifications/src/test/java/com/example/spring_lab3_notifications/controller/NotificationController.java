package com.example.spring_lab3_notifications.controller;

import com.example.spring_lab3_notifications.repository.NotificationRepository;
import com.example.spring_lab3_notifications.repository.UserRepository;
import com.example.spring_lab3_notifications.security.CustomUserDetailsService;
import com.example.spring_lab3_notifications.security.JwtAuthenticationFilter;
import com.example.spring_lab3_notifications.security.JwtService;
import com.example.spring_lab3_notifications.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private NotificationRepository notificationRepository;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void getAllNotifications_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/notifications/all"))
                .andExpect(status().isOk());
    }

    @Test
    void getNotificationById_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/notifications/1"))
                .andExpect(status().isOk());
    }
}
