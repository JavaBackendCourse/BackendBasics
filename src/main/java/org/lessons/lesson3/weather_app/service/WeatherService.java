package org.lessons.lesson3.weather_app.service;

import org.lessons.lesson3.weather_app.models.GetLatitudeLongitudeResponse;
import org.lessons.lesson3.weather_app.models.GetLatitudeLongitudeResults;
import org.lessons.lesson3.weather_app.models.GetWeatherResponse;
import org.lessons.lesson3.weather_app.utils.converters.JsonConverter;
import org.lessons.lesson3.weather_app.utils.exceptions.AppException;
import org.lessons.lesson3.weather_app.utils.statuscodes.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class WeatherService {
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    private final String GEO_API_HOST = "https://geocoding-api.open-meteo.com/v1/";
    private final String WEATHER_API_HOST = "https://api.open-meteo.com/v1/";
    private final String DEFAULT_FORMAT = "json";

    public GetWeatherResponse getWeatherForCity(String city, String language) {
        try {
            GetLatitudeLongitudeResults langLatResponse = getLatitudeLongitude(city, language)
                    .getFirstResult().orElseThrow(
                            () -> new AppException(
                                    "Ошибка при получении погоды!", HttpStatus.INTERNAL_SERVER_ERROR.getCode())
                    );

            GetWeatherResponse response = getWeather(langLatResponse.getLongitude(), langLatResponse.getLatitude());

            log.info("Получен успешный ответ при получении погоды для города {}: {}", city, JsonConverter.toJson(response).orElse(""));
            return response;
        } catch (Exception e) {
            log.error("Получена ошибка при получении погоды для города {}: {}", city, e.getMessage());

            if (e instanceof AppException appErr) {
                throw appErr;
            }

            throw new AppException(
                    "Ошибка при получении погоды!", HttpStatus.INTERNAL_SERVER_ERROR.getCode());
        }
    }

    private GetLatitudeLongitudeResponse getLatitudeLongitude(String city, String language) throws Exception {
        String geoUrl = String.format("%s/search?name=%s&count=1&language=%s&format=%s",
                GEO_API_HOST, city, language, DEFAULT_FORMAT);

        String responseJson = HttpClient.get(geoUrl);

        return JsonConverter.toObject(responseJson, GetLatitudeLongitudeResponse.class)
                .orElseThrow(
                        () -> {
                            log.error("Произошла ошибка при парсинге ответа при получении долготы/широты");

                            return new AppException(
                                    "Ошибка при получении широты/долготы!", HttpStatus.INTERNAL_SERVER_ERROR.getCode());
                        }
                );
    }

    private GetWeatherResponse getWeather(String longitude, String latitude) throws Exception {
        String weatherUrl = String.format(
                "%s/forecast?latitude=%s&longitude=%s&current=temperature_2m,relative_humidity_2m,wind_speed_10m",
                WEATHER_API_HOST, latitude, longitude);

        String responseJson = HttpClient.get(weatherUrl);

        Map<String, Object> responseMap = JsonConverter.toMap(responseJson)
                .orElseThrow(
                        () -> {
                            log.error("Произошла ошибка при парсинге ответа при получении погоды");

                            return new AppException(
                                    "Ошибка при получении погоды!", HttpStatus.INTERNAL_SERVER_ERROR.getCode());
                        }
                );

        Map<String, Object> currentWeatherData = (Map<String, Object>) responseMap.get("current");

        System.out.println("RESPONSE: " + currentWeatherData);

        return new GetWeatherResponse(
                (String) currentWeatherData.get("time"),
                ((Number) currentWeatherData.get("temperature_2m")).doubleValue(),
                ((Number) currentWeatherData.get("relative_humidity_2m")).longValue(),
                ((Number) currentWeatherData.get("wind_speed_10m")).doubleValue()
        );
    }
}
