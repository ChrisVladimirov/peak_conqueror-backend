package com.example.backend.models.DTOs;

import com.example.backend.models.enums.ToughnessEnum;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

public class EditRouteDTO {
    @NotBlank
    @Size(min = 7, max = 30, message = "Route name must be between 7 and 30 characters!")
    private String name;

    @NotBlank
    @Size(min = 30, max = 5000, message = "The route description cannot be shorter than 30 \nor longer than 5000 characters!")
    private String itinerary;

    @NotNull
    @Positive
    private float duration;

    private String coordinatesUrl;

    @NotNull
    private ToughnessEnum toughness;

    @NotEmpty
    private Set<String> pictureUrls;

    public EditRouteDTO() {
        this.pictureUrls = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public EditRouteDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getItinerary() {
        return itinerary;
    }

    public EditRouteDTO setItinerary(String itinerary) {
        this.itinerary = itinerary;
        return this;
    }

    public float getDuration() {
        return duration;
    }

    public EditRouteDTO setDuration(float duration) {
        this.duration = duration;
        return this;
    }

    public String getCoordinatesUrl() {
        return coordinatesUrl;
    }

    public EditRouteDTO setCoordinatesUrl(String coordinatesUrl) {
        this.coordinatesUrl = coordinatesUrl;
        return this;
    }

    public ToughnessEnum getToughness() {
        return toughness;
    }

    public EditRouteDTO setToughness(ToughnessEnum toughness) {
        this.toughness = toughness;
        return this;
    }

    public Set<String> getPictureUrls() {
        return pictureUrls;
    }

    public EditRouteDTO setPictureUrls(Set<String> pictureUrls) {
        this.pictureUrls = pictureUrls;
        return this;
    }
}
