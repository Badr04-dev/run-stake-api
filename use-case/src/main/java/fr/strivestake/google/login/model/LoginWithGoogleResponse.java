package fr.strivestake.google.login.model;

import fr.strivestake.auth.model.AuthStatusEnum;
import fr.strivestake.google.common.model.AuthenticateWithGoogleResponse;
import fr.strivestake.user.model.User;
import lombok.Getter;

@Getter
public class LoginWithGoogleResponse extends AuthenticateWithGoogleResponse {

    private String registrationToken;
    private String suggestedName;
    private String suggestedEmail;

    @Override
    public LoginWithGoogleResponse status(AuthStatusEnum status) {
        this.status = status;
        return this;
    }

    @Override
    public LoginWithGoogleResponse accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    @Override
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
