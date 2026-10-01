package fr.strivestake.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import fr.strivestake.auth.dao.UserAuthProviderCrudDao;
import fr.strivestake.auth.entity.UserAuthProviderEntity;
import fr.strivestake.common.checker.RuleException;
import fr.strivestake.google.login.LoginWithGoogleUseCase;
import fr.strivestake.google.login.model.LoginWithGoogleRequest;
import fr.strivestake.google.login.model.LoginWithGoogleResponse;
import fr.strivestake.google.service.GoogleTokenVerifierService;
import fr.strivestake.user.dao.UserCrudDao;
import fr.strivestake.user.entity.UserEntity;
import fr.strivestake.user.model.AccountStatusEnum;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import static fr.strivestake.auth.model.AuthStatusEnum.AUTHENTICATED;
import static fr.strivestake.auth.model.ProviderEnum.GOOGLE;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class LoginWithGoogleStepDef {

    @Autowired
    private LoginWithGoogleUseCase useCase;

    @Autowired
    private GoogleTokenVerifierService googleTokenVerifierService;

    @Autowired
    private UserCrudDao userCrudDao;

    @Autowired
    private UserAuthProviderCrudDao userAuthProviderCrudDao;

    private LoginWithGoogleResponse response;
    private RuleException thrownException;
    private UserEntity logedInUser;

    @Before
    public void setUp() {
        userAuthProviderCrudDao.deleteAll();
        userCrudDao.deleteAll();
    }

    @Given("a user {string} with email {string}, provider user id {string} and has an account status of {string} exists in the database")
    public void aUserExists(String username, String email, String providerUserId, String accountStatus) {
        logedInUser = new UserEntity()
                .username(username)
                .email(email)
                .accountStatus(AccountStatusEnum.valueOf(accountStatus))
                .createdAt(now());
        userCrudDao.save(logedInUser);

        UserAuthProviderEntity userAuthProviderEntity = new UserAuthProviderEntity()
                .user(logedInUser)
                .provider(GOOGLE)
                .providerUserId(providerUserId)
                .createdAt(now());
        userAuthProviderCrudDao.save(userAuthProviderEntity);
    }

    @When("the user {string} with email {string} and provider user id {string} tries to authenticate with id token {string}")
    public void authenticateWithUsernameAndEmail(String username, String email, String providerUserId, String idToken) {
        GoogleIdToken.Payload payload = initializePayload(username, email, providerUserId);

        mockGoogleTokenVerifications(idToken, payload);

        try {
            response = useCase.login(new LoginWithGoogleRequest().idToken(idToken));
        } catch (RuleException e) {
            thrownException = e;
        }
    }

    @Then("the authentication is granted")
    public void authenticationIsGranted() {
        assertThat(thrownException).isNull();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(AUTHENTICATED);
        assertThat(response.getAccessToken()).isNotNull();
        assertThat(response.getUser()).isNotNull();
        assertThat(response.getUser().getId()).isEqualTo(logedInUser.getId());
    }

    @Then("the authentication is denied")
    public void authenticationIsDenied() {
        assertThat(thrownException).isNotNull();
    }

    @Then("the user must be informed of the violation of {string}")
    public void informedOfRule(String ruleId) {
        assertThat(thrownException.getRuleId()).isEqualTo(ruleId);
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
}
