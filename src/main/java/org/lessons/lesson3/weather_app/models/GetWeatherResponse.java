package org.lessons.lesson3.weather_app.models;

public class GetWeatherResponse {
    private final String time;
    private final Double temperature;
    private final Long humidity;
    private final Double windSpeed;

    public GetWeatherResponse(String time, Double temperature, Long humidity, Double windSpeed) {
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public String getTime() {
        return time;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Long getHumidity() {
        return humidity;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }
}
