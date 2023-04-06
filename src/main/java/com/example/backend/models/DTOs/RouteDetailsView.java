package com.example.backend.models.DTOs;

import com.example.backend.models.entities.CoordinatesFileEntity;
import com.example.backend.models.entities.Like;
import com.example.backend.models.entities.PictureEntity;
import com.example.backend.models.entities.ToughnessLevelEntity;

import java.util.Set;

public class RouteDetailsView {
    private long id;
    private String name;
    private String itinerary;
    private CoordinatesFileEntity coordinates;
    private float duration;
    private ToughnessLevelEntity toughnessLevel;
    private Set<PictureEntity> pictures;
    private Set<Like> usersWhoLiked;
    //private Set<Long> usersWhoLiked;

    public RouteDetailsView() {
    }

    ////////////////////////////
    public String getName() {
        return name;
    }

    public RouteDetailsView setName(String name) {
        this.name = name;
        return this;
    }

    public float getDuration() {
        return duration;
    }

    public RouteDetailsView setDuration(float duration) {
        this.duration = duration;
        return this;
    }

    public String getItinerary() {
        return itinerary;
    }

    public RouteDetailsView setItinerary(String itinerary) {
        this.itinerary = itinerary;
        return this;
    }

    public CoordinatesFileEntity getCoordinates() {
        return coordinates;
    }

    public RouteDetailsView setCoordinates(CoordinatesFileEntity coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public RouteDetailsView setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    public ToughnessLevelEntity getToughnessLevel() {
        return toughnessLevel;
    }

    public RouteDetailsView setToughnessLevel(ToughnessLevelEntity toughnessLevel) {
        this.toughnessLevel = toughnessLevel;
        return this;
    }

    public long getId() {
        return id;
    }

    public RouteDetailsView setId(long id) {
        this.id = id;
        return this;
    }

    public Set<Like> getUsersWhoLiked() {
        return usersWhoLiked;
    }

    public void setUsersWhoLiked(Set<Like> users) {
        this.usersWhoLiked = users;
    }
}
