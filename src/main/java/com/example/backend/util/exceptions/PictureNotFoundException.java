package com.example.backend.util.exceptions;

import com.example.backend.constants.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PictureNotFoundException extends RuntimeException {
    public PictureNotFoundException() {
        super(ErrorMessages.PICTURE_NOT_FOUND.getContent());
    }
}
