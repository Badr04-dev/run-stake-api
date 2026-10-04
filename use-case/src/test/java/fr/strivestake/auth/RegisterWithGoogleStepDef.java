package fr.strivestake.auth;

import fr.strivestake.auth.common.CheckResponseStepDef;
import fr.strivestake.common.checker.RuleException;
import fr.strivestake.google.login.model.LoginWithGoogleResponse;
import fr.strivestake.google.registration.RegisterWithGoogleUseCase;
import fr.strivestake.google.registration.model.RegisterWithGoogleRequest;
import fr.strivestake.google.registration.model.RegisterWithGoogleResponse;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterWithGoogleStepDef {

    @Autowired
    private RegisterWithGoogleUseCase useCase;

    @Autowired
    private CheckResponseStepDef checkResponseStepDef;

    private RegisterWithGoogleResponse response;

    @And("the user with username {string}, email {string} and provider user id {string} tries to register")
    public void registerNewUser(String username, String email, String providerUserId) {
        String registrationToken = getRegistrationTokenFromLoginResponse();

        setData(username, email, providerUserId);

        try {
            response = useCase.register(
                    new RegisterWithGoogleRequest()
                        .registrationToken(registrationToken)
                        .username(username)
            );
            checkResponseStepDef.setResponse(response);
        } catch (RuleException e) {
            checkResponseStepDef.setThrownException(e);
        }
    }

    private String getRegistrationTokenFromLoginResponse() {
        LoginWithGoogleResponse loginResponse = (LoginWithGoogleResponse) checkResponseStepDef.getResponse();
        return loginResponse.getRegistrationToken();
    }

    private void setData(String username, String email, String providerUserId) {
        checkResponseStepDef
                .expectedEmail(email)
                .expectedProviderUserId(providerUserId)
                .expectedUsername(username);
    }

}
