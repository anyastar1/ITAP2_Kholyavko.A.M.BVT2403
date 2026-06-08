package com.example.spring_lab3_notifications.service;

import com.example.spring_lab3_notifications.model.dto.RegisterRequest;
import com.example.spring_lab3_notifications.model.entity.User;
import com.example.spring_lab3_notifications.model.enums.UserRole;
import com.example.spring_lab3_notifications.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private RegisterRequest registerRequest;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest();
        registerRequest.setName("Тест Тестов");
        registerRequest.setEmail("test@example.com");
        registerRequest.setPassword("rawPassword123");
    }

    // ========== ЗАДАНИЕ 5: Тесты для AuthService ==========

    @Test
    void register_ShouldEncodePasswordAndSaveUser() {
        // given
        when(passwordEncoder.encode("rawPassword123")).thenReturn("encodedPassword123");

        // when
        authService.register(registerRequest);

        // then
        verify(passwordEncoder, times(1)).encode("rawPassword123");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void register_ShouldSetUserRoleToUser() {
        // given
        when(passwordEncoder.encode(anyString())).thenReturn("encoded");

        // when
        authService.register(registerRequest);

        // then
        verify(userRepository).save(argThat(user ->
                user.getRole() == UserRole.ROLE_USER
        ));
    }

    @Test
    void register_ShouldSetCreatedAt() {
        // given
        when(passwordEncoder.encode(anyString())).thenReturn("encoded");

        // when
        authService.register(registerRequest);

        // then
        verify(userRepository).save(argThat(user ->
                user.getCreatedAt() != null
        ));
    }

    @Test
    void register_ShouldSetCorrectNameAndEmail() {
        // given
        when(passwordEncoder.encode(anyString())).thenReturn("encoded");

        // when
        authService.register(registerRequest);

        // then
        verify(userRepository).save(argThat(user ->
                user.getName().equals("Тест Тестов") &&
                        user.getEmail().equals("test@example.com")
        ));
    }
}