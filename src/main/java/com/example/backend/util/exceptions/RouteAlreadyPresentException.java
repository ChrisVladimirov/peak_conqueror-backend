package com.example.backend.util.exceptions;

import com.example.backend.constants.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RouteAlreadyPresentException extends RuntimeException {

    public RouteAlreadyPresentException() {
        super(ErrorMessages.ROUTE_ALREADY_PRESENT.getContent());
    }
}
