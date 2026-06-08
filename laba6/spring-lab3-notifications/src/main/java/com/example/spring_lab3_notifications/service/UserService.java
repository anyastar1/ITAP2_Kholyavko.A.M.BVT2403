package com.example.spring_lab3_notifications.service;

import lombok.RequiredArgsConstructor;
import com.example.spring_lab3_notifications.model.dto.UserDto;
import com.example.spring_lab3_notifications.model.entity.User;
import com.example.spring_lab3_notifications.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setDeviceToken(dto.getDeviceToken());
        user.setTelegramChatId(dto.getTelegramChatId());
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }
    public User getUserById(Long id) { return userRepository.findById(id).orElseThrow(); }

    public User updateUser(Long id, UserDto dto) {
        User user = getUserById(id);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setDeviceToken(dto.getDeviceToken());
        user.setTelegramChatId(dto.getTelegramChatId());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) { userRepository.delete(getUserById(id)); }
}
