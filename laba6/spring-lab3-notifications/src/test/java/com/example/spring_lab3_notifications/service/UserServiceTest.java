package com.example.spring_lab3_notifications.service;

import com.example.spring_lab3_notifications.model.dto.UserDto;
import com.example.spring_lab3_notifications.model.entity.User;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserDto userDto;
    private User user;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setName("Иван Иванов");
        userDto.setEmail("ivan@example.com");
        userDto.setPhone("+79990001122");

        user = new User();
        user.setId(1L);
        user.setName("Иван Иванов");
        user.setEmail("ivan@example.com");
    }

    // ========== ЗАДАНИЕ 1: Тест для getUserById() ==========
    @Test
    void getUserById_WhenUserExists_ShouldReturnUser() {
        // given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // when
        User result = userService.getUserById(userId);

        // then
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Иван Иванов", result.getName());
        assertEquals("ivan@example.com", result.getEmail());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUserById_WhenUserNotFound_ShouldThrowException() {
        // given
        Long userId = 999L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // when & then
        assertThrows(RuntimeException.class, () -> userService.getUserById(userId));
        verify(userRepository, times(1)).findById(userId);
    }

    // ========== ЗАДАНИЕ 2: Тест для deleteUser() с verify ==========
    @Test
    void deleteUser_ShouldCallRepositoryDelete() {
        // given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        // when
        userService.deleteUser(userId);

        // then
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void deleteUser_WhenUserNotFound_ShouldThrowException() {
        // given
        Long userId = 999L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // when & then
        assertThrows(RuntimeException.class, () -> userService.deleteUser(userId));
        verify(userRepository, never()).delete(any());
    }

    // ========== ЗАДАНИЕ 6: verify с times(1) ==========
    @Test
    void createUser_ShouldCallSaveExactlyOnce() {
        // given
        when(userRepository.save(any(User.class))).thenReturn(user);

        // when
        userService.createUser(userDto);

        // then
        verify(userRepository, times(1)).save(any(User.class));
        verify(userRepository, atMost(1)).save(any(User.class));
    }
}