package com.example.spring_lab3_notifications.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.spring_lab3_notifications.model.dto.UserDto;
import com.example.spring_lab3_notifications.model.entity.User;
import com.example.spring_lab3_notifications.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    // УДАЛИТЕ ЭТУ СТРОКУ: private final UserRepository userRepository;

    @PostMapping("/add")
    public UserDto createUser(@RequestBody @Valid UserDto dto) {
        User user = userService.createUser(dto);
        return toDto(user);
    }

    @GetMapping("/all")
    public List<UserDto> getAll() {
        return userService.getAllUsers().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return toDto(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody @Valid UserDto dto) {
        return toDto(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    private UserDto toDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .deviceToken(user.getDeviceToken())
                .telegramChatId(user.getTelegramChatId())
                .createdAt(user.getCreatedAt())
                .build();
    }
}