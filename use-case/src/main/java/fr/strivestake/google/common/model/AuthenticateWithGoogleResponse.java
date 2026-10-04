package fr.strivestake.google.common.model;

import fr.strivestake.auth.model.AuthStatusEnum;
import fr.strivestake.user.model.User;
import lombok.Getter;

@Getter
public class AuthenticateWithGoogleResponse {

    protected AuthStatusEnum status;
    protected String accessToken;
    protected User user;

    public AuthenticateWithGoogleResponse status(AuthStatusEnum status) {
        this.status = status;
        return this;
    }

    public AuthenticateWithGoogleResponse accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public AuthenticateWithGoogleResponse user(User user) {
        this.user = user;
        return this;
    }
}
