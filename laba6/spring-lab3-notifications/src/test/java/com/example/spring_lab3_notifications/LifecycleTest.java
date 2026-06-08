package com.example.spring_lab3_notifications;

import org.junit.jupiter.api.*;

public class LifecycleTest {
    @BeforeEach
    void setUp() {
        System.out.println("Перед каждым тестом");
    }

    @AfterEach
    void tearDown() {
        System.out.println("После каждого теста");
    }

    @Test
    void firstTest() {
        System.out.println("Первый тест");
    }

    @Test
    void secondTest() {
        System.out.println("Второй тест");
    }
}
