package com.example;
import java.time.LocalDate;

public class User {

    private String name;
    private int age;
    private LocalDate registeredAt;

    public User() {
    }

    public User(String name, int age, LocalDate registeredAt) {
        this.name = name;
        this.age = age;
        this.registeredAt = registeredAt;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getRegisteredAt() {
        return registeredAt;
    }
}