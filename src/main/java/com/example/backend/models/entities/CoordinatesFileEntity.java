package com.example.backend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "coordinates_files")
public class CoordinatesFileEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String url;

    public CoordinatesFileEntity() {}

    public CoordinatesFileEntity(String title, String url) {
        this.setTitle(title);
        this.setUrl(url);
    }

    public String getTitle() {
        return title;
    }

    public CoordinatesFileEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public CoordinatesFileEntity setUrl(String url) {
        this.url = url;
        return this;
    }
}
