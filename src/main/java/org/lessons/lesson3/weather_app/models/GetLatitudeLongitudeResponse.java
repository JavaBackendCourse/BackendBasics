package org.lessons.lesson3.weather_app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLatitudeLongitudeResponse {
    private List<GetLatitudeLongitudeResults> results;

    public GetLatitudeLongitudeResponse() {
    }

    public Optional<GetLatitudeLongitudeResults> getFirstResult() {
        if (results.isEmpty()) return Optional.empty();

        return Optional.of(results.getFirst());
    }

    public List<GetLatitudeLongitudeResults> getResults() {
        return results;
    }

    public void setResults(List<GetLatitudeLongitudeResults> results) {
        this.results = results;
    }
}
