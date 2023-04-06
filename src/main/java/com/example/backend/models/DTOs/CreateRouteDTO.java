package com.example.backend.models.DTOs;

import com.example.backend.models.enums.ToughnessEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.HashSet;
import java.util.Set;

public class CreateRouteDTO {

    @NotBlank(message = "Route name should not be blank!")
    private String name;

    @NotBlank(message = "Route description should not be blank!")
    private String itinerary;

    @Positive(message = "Duration value must be positive!")
    @NotNull(message = "Duration cannot be left empty!")
    private float duration;

    private String coordinatesUrl;

    @NotNull(message = "Route difficulty level is mandatory to specify!")
    private String toughness;

    @NotEmpty(message = "At least one image address should be specified!")
    private Set<String> pictureUrls;

    public CreateRouteDTO() {
        this.pictureUrls = new HashSet<>();
    }

    ///////////////////////////////////
    public String getName() {
        return name;
    }

    public CreateRouteDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getItinerary() {
        return itinerary;
    }

    public CreateRouteDTO setItinerary(String itinerary) {
        this.itinerary = itinerary;
        return this;
    }

    public float getDuration() {
        return duration;
    }

    public CreateRouteDTO setDuration(float duration) {
        this.duration = duration;
        return this;
    }

    public String getCoordinatesUrl() {
        return coordinatesUrl;
    }

    public CreateRouteDTO setCoordinatesUrl(String coordinatesUrl) {
        this.coordinatesUrl = coordinatesUrl;
        return this;
    }

    public String getToughness() {
        return toughness;
    }

    public CreateRouteDTO setToughness(String toughness) {
        this.toughness = toughness;
        return this;
    }

    public Set<String> getPictureUrls() {
        return pictureUrls;
    }

    public CreateRouteDTO setPictureUrls(Set<String> pictureUrls) {
        this.pictureUrls = pictureUrls;
        return this;
    }
}
