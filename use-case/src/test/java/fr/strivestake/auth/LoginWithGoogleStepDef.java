package fr.strivestake.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import fr.strivestake.auth.common.CheckResponseStepDef;
import fr.strivestake.google.service.JwtService;
import fr.strivestake.common.checker.RuleException;
import fr.strivestake.auth.google.login.LoginWithGoogleUseCase;
import fr.strivestake.auth.google.login.model.LoginWithGoogleRequest;
import fr.strivestake.auth.google.login.model.LoginWithGoogleResponse;
import fr.strivestake.google.model.RegistrationClaims;
import fr.strivestake.google.service.GoogleTokenVerifierService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import static fr.strivestake.common.auth.model.AuthStatusEnum.REGISTRATION_REQUIRED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Getter
@Setter
public class LoginWithGoogleStepDef {

    @Autowired
    private LoginWithGoogleUseCase useCase;

    @Autowired
    private GoogleTokenVerifierService googleTokenVerifierService;

    @Autowired
    private CheckResponseStepDef checkResponseStepDef;

    @Autowired
    private JwtService jwtService;

    private LoginWithGoogleResponse response;

    @When("the user {string} with email {string} and provider user id {string} tries to login with id token {string}")
    public void loginAnExistingUser(String username, String email, String providerUserId, String idToken) {
        GoogleIdToken.Payload payload = initializePayload(username, email, providerUserId);

        mockGoogleTokenVerifications(idToken, payload);

        setData(username, email, providerUserId);

        try {
            response = useCase.login(new LoginWithGoogleRequest().idToken(idToken));
            checkResponseStepDef.setResponse(response);
        } catch (RuleException e) {
            checkResponseStepDef.setThrownException(e);
        }
    }

    @When("the user with google username {string}, email {string} and provider user id {string} tries to login with id token {string}")
    public void authenticateNewUser(String googleUsername, String email, String providerUserId, String idToken) {
        GoogleIdToken.Payload payload = initializePayload(googleUsername, email, providerUserId);

        mockGoogleTokenVerifications(idToken, payload);

        setData(googleUsername, email, providerUserId);

        try {
            response = useCase.login(new LoginWithGoogleRequest().idToken(idToken));
            checkResponseStepDef.setResponse(response);
        } catch (RuleException e) {
            checkResponseStepDef.setThrownException(e);
        }
    }

    @Then("the user is required to register")
    public void userRequiredToRegister() {
        assertThat(checkResponseStepDef.getThrownException()).isNull();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(REGISTRATION_REQUIRED);
        assertThat(response.getAccessToken()).isNull();
        assertThatRegistrationTokenIsCorrect();
        assertThat(response.getUser()).isNull();
        assertThat(response.getSuggestedName()).isNotNull();
        assertThat(response.getSuggestedEmail()).isNotNull();
    }

    private GoogleIdToken.@NonNull Payload initializePayload(String username, String email, String providerUserId) {
        GoogleIdToken.Payload payload = new GoogleIdToken.Payload();
        payload.setEmail(email);
        payload.setSubject(providerUserId);
        payload.set("name", username);
        return payload;
    }

    private void mockGoogleTokenVerifications(String idToken, GoogleIdToken.Payload payload) {
        when(googleTokenVerifierService.verify(idToken)).thenReturn(payload);
    }

    private void assertThatRegistrationTokenIsCorrect() {
        assertThat(response.getRegistrationToken()).isNotNull();
        RegistrationClaims claims = jwtService.parseRegistrationToken(response.getRegistrationToken());
        assertThat(claims.googleSub()).isEqualTo(checkResponseStepDef.getExpectedProviderUserId());
        assertThat(claims.email()).isEqualTo(checkResponseStepDef.getExpectedEmail());
    }

    private void setData(String username, String email, String providerUserId) {
        checkResponseStepDef
                .expectedUsername(username)
                .expectedEmail(email)
                .expectedProviderUserId(providerUserId);
    }
}
