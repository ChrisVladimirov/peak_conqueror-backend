package com.example.backend.constants;

public enum ErrorMessages {
    USER_ROLE_NOT_FOUND("No role with this enum found!"),
    USER_WITH_INVALID_ID("No user with this id found!"),
    USER_NOT_FOUND("No user with this username or password found!"),
    ROUTE_ALREADY_PRESENT("Such a route is already present!"),
    USER_ALREADY_REGISTERED("Username already taken!"),
    ROUTE_NOT_FOUND("No such route found!"),
    INVALID_EMAIL("Invalid email!"),
    PICTURE_NOT_FOUND("No picture with this id found!"),
    ROUTE_NOT_LIKED_BY_THIS_USER("No such route liked by this user!");
    private final String content;

    public String getContent() {
        return content;
    }

    ErrorMessages(String content) {
        this.content = content;
    }
}
