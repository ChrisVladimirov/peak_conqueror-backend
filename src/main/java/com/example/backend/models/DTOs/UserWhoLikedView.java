package com.example.backend.models.DTOs;

public class UserWhoLikedView {

    private long id;

    private String username;

    public UserWhoLikedView() {
    }

    public UserWhoLikedView(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public UserWhoLikedView setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserWhoLikedView setUsername(String username) {
        this.username = username;
        return this;
    }
}
