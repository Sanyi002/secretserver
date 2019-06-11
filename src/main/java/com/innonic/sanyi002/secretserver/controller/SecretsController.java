package com.innonic.sanyi002.secretserver.controller;

import com.innonic.sanyi002.secretserver.exception.SecretInvalidInputException;
import com.innonic.sanyi002.secretserver.exception.SecretNotFoundException;
import com.innonic.sanyi002.secretserver.model.Secret;
import com.innonic.sanyi002.secretserver.service.SecretService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/v1")
public class SecretsController {

    @Autowired
    private SecretService secretService;

    @GetMapping(value = "/secret/{hash}",
            produces = {"application/json", "application/xml"})
    @ResponseBody
    public Secret getSecretByHash(
            @ApiParam(value = "Unique hash to identify the secret", required = true)
            @PathVariable final String hash) {
        try {
            return secretService.getSecretByHash(hash);
        } catch (SecretNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Secret not found", ex);
        }

    }

    @PostMapping(value = "/secret",
            produces = {"application/json", "application/xml"})
    public Secret addSecret(
            @ApiParam(value = "This text will be saved as a secret", required = true) @RequestParam String secret,
            @ApiParam(value = "The secret won't be available after the given time. The value is provided in minutes. 0 means never expires", required = true) @RequestParam int expireAfter,
            @ApiParam(value = "The secret won't be available after the given number of views. It must be greater than 0", required = true) @RequestParam Integer expireAfterViews) {
        try {
            return secretService.addSecret(secret, expireAfter, expireAfterViews);
        } catch (SecretInvalidInputException ex) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Invalid input", ex);
        }
    }


}
