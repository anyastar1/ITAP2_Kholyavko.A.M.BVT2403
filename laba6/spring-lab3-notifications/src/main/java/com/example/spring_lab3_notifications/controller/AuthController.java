package com.example.spring_lab3_notifications.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.spring_lab3_notifications.model.dto.RegisterRequest;
import com.example.spring_lab3_notifications.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Пользователь успешно зарегистрирован");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<Map<String, String>> registerAdmin(@RequestBody @Valid RegisterRequest request) {
        authService.registerAdmin(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Администратор успешно зарегистрирован");
        return ResponseEntity.ok(response);
    }
}
