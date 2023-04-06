package com.example.backend.models.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PictureCreateDTO {

    @NotBlank(message = "Picture title should be descriptive and concise but not blank!")
    @Size(min = 5, max = 25, message = "Picture title must be between 5 and 15 characters long!")
    private String title;

    @NotBlank(message = "Picture url should not be blank!")
    @Size(min = 20, max = 200, message = "Picture url must be between 20 and 200 characters long!")
    private String url;

    public PictureCreateDTO(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public PictureCreateDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureCreateDTO setUrl(String url) {
        this.url = url;
        return this;
    }
}
