package fr.strivestake.google.login.model;

import lombok.Getter;
import org.jspecify.annotations.NonNull;

@Getter
public class LoginWithGoogleRequest {

    private String idToken;

    public LoginWithGoogleRequest idToken(String idToken) {
        this.idToken = idToken;
        return this;
    }

}
