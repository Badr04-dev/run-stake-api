package fr.strivestake.auth.google.login.model;

import lombok.Getter;

@Getter
public class LoginWithGoogleRequest {

    private String idToken;

    public LoginWithGoogleRequest idToken(String idToken) {
        this.idToken = idToken;
        return this;
    }

}
