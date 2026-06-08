package com.example.spring_lab3_notifications.controller;

import com.example.spring_lab3_notifications.repository.NotificationRepository;
import com.example.spring_lab3_notifications.repository.UserRepository;
import com.example.spring_lab3_notifications.security.CustomUserDetailsService;
import com.example.spring_lab3_notifications.security.JwtAuthenticationFilter;
import com.example.spring_lab3_notifications.security.JwtService;
import com.example.spring_lab3_notifications.service.NotificationService;
import com.example.spring_lab3_notifications.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Моки для UserController
    @MockBean
    private UserService userService;

    // Моки для Security (все обязательные)
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // Моки для NotificationController (он тоже загружается в контекст)
    @MockBean
    private NotificationService notificationService;

    @MockBean
    private NotificationRepository notificationRepository;

    @Test
    void shouldReturnOkWhenGetAllUsers() throws Exception {
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkWhenGetUserById() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk());
    }
}