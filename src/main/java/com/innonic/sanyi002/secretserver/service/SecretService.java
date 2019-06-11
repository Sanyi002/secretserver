package com.innonic.sanyi002.secretserver.service;

import com.innonic.sanyi002.secretserver.exception.SecretInvalidInputException;
import com.innonic.sanyi002.secretserver.exception.SecretNotFoundException;
import com.innonic.sanyi002.secretserver.model.Secret;
import com.innonic.sanyi002.secretserver.repository.SecretJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Component
public class SecretService {

    @Autowired
    private SecretJpaRepository secretJpaRepository;

    /**
     * Push new secret to the database.
     * @param secret the secret itself
     * @param expireAfter the secret won't be available after the given time
     * @param expireAfterViews the secret won't be available after the given number of views
     * @return new secret
     */
    public Secret addSecret(String secret, Integer expireAfter, Integer expireAfterViews) throws SecretInvalidInputException {
        Secret newSecret = null;
        if(expireAfterViews > 0) {
            newSecret = new Secret(secret, expireAfter, expireAfterViews);
            secretJpaRepository.save(newSecret);
            newSecret = secretJpaRepository.getSecretByHash(newSecret.getHash());
        } else {
            throw new SecretInvalidInputException("Invalid input");
        }
        return newSecret;
    }

    /**
     * Get secret based on hash.
     * @param hash
     * @return secret
     * @throws SecretNotFoundException throws when the secret not found in the db or it's not viewable
     */
    public Secret getSecretByHash(String hash) throws SecretNotFoundException {
        Secret secret = null;
        if(isAvailable(hash))
            secret = secretJpaRepository.getSecretByHash(hash);

        return secret;
    }

    /**
     * Check if the secret is avalaible.
     * @param hash
     * @return
     * @throws SecretNotFoundException throws when the secret not found in the db or it's not viewable
     */
    private boolean isAvailable(String hash) throws SecretNotFoundException {
        Secret secret = secretJpaRepository.getSecretByHash(hash);
        if(secret == null)
            throw new SecretNotFoundException("Secret not found");

        updateRemainingViews(secret);
        if(!isExpired(secret) && isViewable(secret)) {
            return true;
        } else {
            throw new SecretNotFoundException("Secret not found");
        }
    }

    /**
     *  Reduce the number of views.
     * @param secret
     */
    private void updateRemainingViews(Secret secret) {
        secret.setRemainingViews(secret.getRemainingViews() - 1);
        secretJpaRepository.save(secret);
    }

    /**
     * Check if the secret is viewable.
     * @param secret
     * @return
     */
    private boolean isViewable(Secret secret) {
        if(secret.getRemainingViews() >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the secret is still not expired.
     * @param secret
     * @return
     */
    private boolean isExpired(Secret secret) {
        if(secret.getExpiresAt().compareTo(LocalDateTime.now()) < 0 && !secret.getExpiresAt().equals(secret.getCreatedAt())) {
            return true;
        } else {
            return false;
        }
    }

}
