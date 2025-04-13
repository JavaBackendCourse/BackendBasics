package org.lessons.lesson2.rest.util.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Optional;

public class JsonConverter {
    private static final ObjectMapper mapper = new ObjectMapper()
            .findAndRegisterModules().registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static Optional<String> toJson(Object obj) {
        try {
            String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            return Optional.of(result);
        } catch (Exception e) {
            System.out.println("Ошибка конвертации в JSON: " + e.getMessage());
            return Optional.empty();
        }
    }

    public static <T> Optional<T> toObject(String json, Class<T> clazz) {
        try {
            T result = mapper.readValue(json, clazz);
            return Optional.of(result);
        } catch (Exception e) {
            System.out.println("Ошибка конвертации из JSON: " + e.getMessage());
            return Optional.empty();
        }
    }
}
