package fr.strivestake.google.login.model;

import fr.strivestake.auth.model.AuthStatusEnum;
import fr.strivestake.user.model.User;
import lombok.Getter;

@Getter
public class LoginWithGoogleResponse {

    // Both Responses
    private AuthStatusEnum status;

    // Response 1
    private String accessToken;
    private User user;

    // Response 2
    private String registrationToken;
    private String suggestedName;
    private String suggestedEmail;

    public LoginWithGoogleResponse status(AuthStatusEnum status) {
        this.status = status;
        return this;
    }

    public LoginWithGoogleResponse accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public LoginWithGoogleResponse user(User user) {
        this.user = user;
        return this;
    }

    public LoginWithGoogleResponse registrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
        return this;
    }

    public LoginWithGoogleResponse suggestedName(String suggestedName) {
        this.suggestedName = suggestedName;
        return this;
    }

    public LoginWithGoogleResponse suggestedEmail(String suggestedEmail) {
        this.suggestedEmail = suggestedEmail;
        return this;
    }
}
