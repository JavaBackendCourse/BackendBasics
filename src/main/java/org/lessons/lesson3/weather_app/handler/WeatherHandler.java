package org.lessons.lesson3.weather_app.handler;

import com.sun.net.httpserver.HttpExchange;
import org.lessons.lesson2.rest.util.http.HttpStatus;
import org.lessons.lesson3.weather_app.models.GetWeatherResponse;
import org.lessons.lesson3.weather_app.service.WeatherService;
import org.lessons.lesson3.weather_app.utils.QueryParser;
import org.lessons.lesson3.weather_app.utils.converters.JsonConverter;
import org.lessons.lesson3.weather_app.utils.exceptions.AppException;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class WeatherHandler extends BaseHandler {
    private final WeatherService service = new WeatherService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            URI requestURI = exchange.getRequestURI();
            Map<String, String> params = QueryParser.parse(requestURI.getQuery());

            String city = params.getOrDefault("city", "London").trim();
            String lang = params.getOrDefault("lang", "en").toLowerCase().trim();

            try {
                GetWeatherResponse weatherResponse = service.getWeatherForCity(city, lang);
                String jsonResponse = JsonConverter.toJson(weatherResponse).orElse("{}");

                sendResponse(exchange, HttpStatus.OK.getCode(), jsonResponse);
            } catch (Exception e) {
                if (e instanceof AppException appErr) {
                    String jsonResponse = JsonConverter.toJson(appErr).orElse("{}");
                    sendResponse(exchange, HttpStatus.OK.getCode(), jsonResponse);
                }

                String jsonResponse = JsonConverter.toJson(
                        new AppException(
                                "Непредвиденная ошибка!",
                                HttpStatus.INTERNAL_SERVER_ERROR.getCode()
                        )
                ).orElse("{}");

                sendResponse(exchange, HttpStatus.OK.getCode(), jsonResponse);
            }
        }
    }
}
