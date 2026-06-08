package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.UserDto;
import org.example.model.entity.User;
import org.example.service.UserMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.example.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/add")
    public UserDto createUser(@RequestBody @Valid UserDto request) {
        User response = userService.createUser(request);
        return userMapper.toDto(response);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User response = userService.getUserById(id);
        return userMapper.toDto(response);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserDto request) {
        User response = userService.updateUser(id, request);
        return userMapper.toDto(response);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return String.format("Пользователь %s удален", id);
    }
}