package fr.strivestake.google.registration.model;

import fr.strivestake.auth.model.AuthStatusEnum;
import lombok.Getter;

@Getter
public class RegisterWithGoogleResponse {

    private AuthStatusEnum status;
    private String accessToken;
    private Long userId;

    public RegisterWithGoogleResponse status(AuthStatusEnum status) {
        this.status = status;
        return this;
    }

    public RegisterWithGoogleResponse accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public RegisterWithGoogleResponse userId(Long userId) {
        this.userId = userId;
        return this;
    }
}
