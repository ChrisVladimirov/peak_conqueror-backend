package com.example.backend.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.example.backend.constants.ErrorMessages.USER_ALREADY_REGISTERED;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException() {
        super(USER_ALREADY_REGISTERED.getContent());
    }
}
