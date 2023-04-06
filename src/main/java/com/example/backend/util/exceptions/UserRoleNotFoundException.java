package com.example.backend.util.exceptions;

import com.example.backend.constants.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserRoleNotFoundException extends RuntimeException {

    public UserRoleNotFoundException() {
        super(ErrorMessages.USER_ROLE_NOT_FOUND.getContent());
    }

    public UserRoleNotFoundException(String message) {
        super(message);
    }
}
