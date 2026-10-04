package fr.strivestake.google.registration.model;

import fr.strivestake.auth.model.AuthStatusEnum;
import fr.strivestake.google.common.model.AuthenticateWithGoogleResponse;
import fr.strivestake.user.model.User;
import lombok.Getter;

@Getter
public class RegisterWithGoogleResponse extends AuthenticateWithGoogleResponse {

    @Override
    public RegisterWithGoogleResponse status(AuthStatusEnum status) {
        this.status = status;
        return this;
    }

    @Override
    public RegisterWithGoogleResponse accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    @Override
    public RegisterWithGoogleResponse user(User user) {
        this.user = user;
        return this;
    }
}
