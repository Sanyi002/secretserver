package com.innonic.sanyi002.secretserver.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class Secret {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @ApiModelProperty(notes = "Unique hash to identify the secrets")
    private String hash;

    @ApiModelProperty(notes = "The secret itself")
    private String secretText;

    @ApiModelProperty(notes = "The date and time of the creation")
    private LocalDateTime createdAt;

    @ApiModelProperty(notes = "The secret cannot be reached after this time")
    private LocalDateTime expiresAt;

    @ApiModelProperty(notes = "How many times the secret can be viewed")
    private Integer remainingViews;

    public Secret() {
    }

    public Secret(String secretText, Integer expireAfter, Integer expireAfterViews) {
        this.secretText = secretText;
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        this.expiresAt = LocalDateTime.now().plusMinutes(expireAfter).truncatedTo(ChronoUnit.MINUTES);
        this.remainingViews = expireAfterViews;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSecretText() {
        return secretText;
    }

    public void setSecretText(String secretText) {
        this.secretText = secretText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Integer getRemainingViews() {
        return remainingViews;
    }

    public void setRemainingViews(Integer remainingViews) {
        this.remainingViews = remainingViews;
    }
}
