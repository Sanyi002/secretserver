package com.innonic.sanyi002.secretserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Secret not found")
public class SecretNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public SecretNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
