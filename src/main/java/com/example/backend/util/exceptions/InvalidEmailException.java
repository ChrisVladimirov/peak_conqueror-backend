package com.example.backend.util.exceptions;

import com.example.backend.constants.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException() {
        super(ErrorMessages.INVALID_EMAIL.getContent());
    }
}
