package fr.strivestake.auth.common;

import fr.strivestake.common.auth.service.JwtService;
import fr.strivestake.common.checker.RuleException;
import fr.strivestake.google.common.model.AuthenticateWithGoogleResponse;
import fr.strivestake.google.login.model.LoginWithGoogleResponse;
import fr.strivestake.google.model.AccessTokenClaims;
import io.cucumber.java.en.Then;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import static fr.strivestake.auth.model.AuthStatusEnum.AUTHENTICATED;
import static fr.strivestake.auth.model.AuthStatusEnum.REGISTERED;
import static org.assertj.core.api.Assertions.assertThat;

@Getter
@Setter
public class CheckResponseStepDef {

    @Autowired
    private JwtService jwtService;

    private RuleException thrownException;
    private AuthenticateWithGoogleResponse response;
    private String expectedUsername;
    private String expectedEmail;
    private String expectedProviderUserId;

    @Then("the authentification request is granted")
    public void authenticationIsGranted() {
        assertThat(thrownException).isNull();
        assertThat(response).isNotNull();
        assertThatStatusIsCorrect();
        assertThatAccessTokenIsCorrect();
        assertThatUserIsCorrect();
    }

    @Then("the authentication is denied")
    public void authenticationIsDenied() {
        assertThat(thrownException).isNotNull();
    }

    @Then("the user must be informed of the violation of {string}")
    public void informedOfRule(String ruleId) {
        assertThat(thrownException.getRuleId()).isEqualTo(ruleId);
    }

    private void assertThatStatusIsCorrect() {
        if (response instanceof LoginWithGoogleResponse) {
            assertThat(response.getStatus()).isEqualTo(AUTHENTICATED);
        } else {
            assertThat(response.getStatus()).isEqualTo(REGISTERED);
        }
    }

    private void assertThatAccessTokenIsCorrect() {
        assertThat(response.getAccessToken()).isNotNull();
        AccessTokenClaims claims = jwtService.parseAccessToken(response.getAccessToken());
        assertThat(claims.googleSub()).isEqualTo(expectedProviderUserId);
        assertThat(claims.email()).isEqualTo(expectedEmail);
    }

    private void assertThatUserIsCorrect() {
        assertThat(response.getUser()).isNotNull();
        assertThat(response.getUser().getUsername()).isEqualTo(expectedUsername);
    }

    public CheckResponseStepDef expectedProviderUserId(String expectedProviderUserId) {
        this.expectedProviderUserId = expectedProviderUserId;
        return this;
    }

    public CheckResponseStepDef expectedEmail(String expectedEmail) {
        this.expectedEmail = expectedEmail;
        return this;
    }

    public CheckResponseStepDef expectedUsername(String expectedUsername) {
        this.expectedUsername = expectedUsername;
        return this;
    }
}
