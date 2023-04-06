package com.example.backend.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
public class RouteEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true, length = 5000)
    private String itinerary;

    @OneToOne
    private CoordinatesFileEntity coordinates;

    @Column(nullable = false, precision = 3)
    private float duration; //in hours, e.g. 2.5h

    @ManyToOne(targetEntity = ToughnessLevelEntity.class)
    private ToughnessLevelEntity toughnessLevel;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<PictureEntity> pictures;

    /*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "routes_users", joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> usersWhoLiked;*/

    public RouteEntity() {
        this.pictures = new HashSet<>();
        //this.usersWhoLiked = new HashSet<>();
    }

    public void addPicture(PictureEntity picture) {
        this.getPictures().add(picture);
    }

    public void removePicture(PictureEntity picture) {
        this.getPictures().remove(picture);
    }

    ///////////////////////////////////////////////////////////////
    public String getName() {
        return name;
    }

    public RouteEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getItinerary() {
        return itinerary;
    }

    public RouteEntity setItinerary(String itinerary) {
        this.itinerary = itinerary;
        return this;
    }

    public CoordinatesFileEntity getCoordinates() {
        return coordinates;
    }

    public RouteEntity setCoordinates(CoordinatesFileEntity coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public float getDuration() {
        return duration;
    }

    public RouteEntity setDuration(float duration) {
        this.duration = duration;
        return this;
    }

    public ToughnessLevelEntity getToughnessLevel() {
        return toughnessLevel;
    }

    public RouteEntity setToughnessLevel(ToughnessLevelEntity toughnessLevel) {
        this.toughnessLevel = toughnessLevel;
        return this;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public RouteEntity setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    /*public long getLikes() {
        return this.usersWhoLiked.size();
    }

    public Set<UserEntity> getUsersWhoLiked() {
        return usersWhoLiked;
    }

    public RouteEntity setUsersWhoLiked(Set<UserEntity> usersWhoLiked) {
        this.usersWhoLiked = usersWhoLiked;
        return this;
    }*/
}
