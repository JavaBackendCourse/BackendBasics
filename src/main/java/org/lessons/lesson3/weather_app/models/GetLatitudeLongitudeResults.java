package org.lessons.lesson3.weather_app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLatitudeLongitudeResults {
    private String latitude;
    private String longitude;

    public GetLatitudeLongitudeResults(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GetLatitudeLongitudeResults() {}

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
