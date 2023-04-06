package com.example.backend.models.DTOs;

public class LoggedUserView extends UserDetailsView {

    private String token;

    public String getToken() {
        return token;
    }

    public LoggedUserView setToken(String token) {
        this.token = token;
        return this;
    }
}
