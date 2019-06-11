package com.innonic.sanyi002.secretserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED, reason = "Invalid input")
public class SecretInvalidInputException extends Exception {
    private static final long serialVersionUID = 1L;

    public SecretInvalidInputException(String errorMessage) {
        super(errorMessage);
    }

}
