package org.lessons.lesson3.weather_app.utils.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JsonConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules().registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static Optional<String> toJson(Object object) {
        try {
            String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            return Optional.of(result);
        } catch (Exception e) {
            System.out.printf("Json write value error: %s\n", e.getMessage());
            return Optional.empty();
        }
    }

    public static Optional<Map<String, Object>> toMap(String json) {
        try {
            TypeReference<HashMap<String, Object>> typeRef
                    = new TypeReference<HashMap<String, Object>>() {
            };
            HashMap<String, Object> result = objectMapper.readValue(json, typeRef);

            return Optional.of(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static <T> Optional<T> toObject(String json, Class<T> clazz) {
        try {
            T result = objectMapper.readValue(json, clazz);
            return Optional.of(result);
        } catch (Exception e) {
            System.out.printf("Json read value error: %s\n", e.getMessage());
            return Optional.empty();
        }
    }
}
