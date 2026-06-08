package com.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonService {

    private static final Logger logger = LoggerFactory.getLogger(JsonService.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    public static String toJson(User user) throws Exception {
        String json = mapper.writeValueAsString(user);
        logger.info("Объект сериализован в JSON: {}", json);
        return json;
    }

    public static User fromJson(String json) throws Exception {
        User user = mapper.readValue(json, User.class);
        logger.info("Объект десериализован из JSON");
        return user;
    }
}