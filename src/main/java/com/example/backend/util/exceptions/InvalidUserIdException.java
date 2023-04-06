package com.example.backend.util.exceptions;

import com.example.backend.constants.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidUserIdException extends RuntimeException {

    public InvalidUserIdException() {
        super(ErrorMessages.USER_WITH_INVALID_ID.getContent());
    }
}
