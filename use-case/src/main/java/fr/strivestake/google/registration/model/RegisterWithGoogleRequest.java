package fr.strivestake.google.registration.model;

import lombok.Getter;

@Getter
public class RegisterWithGoogleRequest {

    private String registrationToken;
    private String username;

    public RegisterWithGoogleRequest registrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
        return this;
    }

    public RegisterWithGoogleRequest username(String username) {
        this.username = username;
        return this;
    }
}
