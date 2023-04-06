package com.example.backend.util.exceptions;

import com.example.backend.constants.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RouteNotFoundException extends RuntimeException {

    public RouteNotFoundException() {
        super(ErrorMessages.ROUTE_NOT_FOUND.getContent());
    }
}
