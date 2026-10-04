package fr.strivestake.auth.google.registration.model;

import fr.strivestake.common.auth.model.AuthStatusEnum;
import fr.strivestake.auth.google.common.model.AuthenticateWithGoogleResponse;
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
