package com.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws Exception {

        int[] numbers = {10, 20, 30, 40, 50};
        double sum = 0;

        for (int n : numbers) sum += n;

        double average = sum / numbers.length;
        logger.info("Среднее значение массива: {}", average);

        User user = new User("Аня", 19, LocalDate.now());

        String json = JsonService.toJson(user);

        User restored = JsonService.fromJson(json);

        logger.info("Имя после десериализации: {}", restored.getName());
    }
}