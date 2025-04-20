package org.lessons.lesson3.weather_app.utils;

import java.util.HashMap;
import java.util.Map;

public class QueryParser {
    public static Map<String, String> parse(String query) {
        Map<String, String> result = new HashMap<>();
        if (query == null) return result;

        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            }
        }
        return result;
    }
}
