package fr.strivestake.google.login;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import fr.strivestake.auth.model.UserAuthProvider;
import fr.strivestake.auth.service.CreateUserAuthProviderService;
import fr.strivestake.auth.service.SearchUserAuthProviderService;
import fr.strivestake.common.auth.service.JwtService;
import fr.strivestake.common.checker.RuleException;
import fr.strivestake.google.login.model.LoginWithGoogleRequest;
import fr.strivestake.google.login.model.LoginWithGoogleResponse;
import fr.strivestake.google.service.GoogleTokenVerifierService;
import fr.strivestake.user.model.User;
import fr.strivestake.user.rules.UserChecker;
import fr.strivestake.user.service.SearchUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static fr.strivestake.auth.model.ProviderEnum.GOOGLE;
import static fr.strivestake.auth.model.AuthStatusEnum.AUTHENTICATED;
import static fr.strivestake.auth.model.AuthStatusEnum.REGISTRATION_REQUIRED;
import static fr.strivestake.user.rules.UserRules.RULE_0004;
import static java.time.LocalDateTime.now;

@Transactional
@Component
@RequiredArgsConstructor
public class LoginWithGoogleUseCase {

    private final UserChecker checker;
    private final GoogleTokenVerifierService verifier;
    private final SearchUserService searchUserService;
    private final SearchUserAuthProviderService searchUserAuthProviderService;
    private final CreateUserAuthProviderService createUserAuthProviderService;
    private final JwtService jwtService;

    public LoginWithGoogleResponse login(LoginWithGoogleRequest request) throws RuleException {
        GoogleIdToken.Payload payload = verifier.verify(request.getIdToken());
        Optional<User> userToLogin = searchUserService.findUserByEmail(payload.getEmail());
        if (userToLogin.isEmpty()) {
            return registrationRequired((String) payload.get("name"), payload.getEmail(), payload.getSubject());
        }
        throwIfInvalid(userToLogin.get());
        addProviderIfNotExists(payload, userToLogin);
        return login(userToLogin.get(), payload.getSubject());
    }

    private void addProviderIfNotExists(GoogleIdToken.Payload payload, Optional<User> userToLogin) {
        if(searchUserAuthProviderService.hasNotSameAuthProvider(GOOGLE, payload.getSubject())) {
            UserAuthProvider userAuthProvider = toUserAuthProvider(userToLogin.get(), payload.getSubject());
            createUserAuthProviderService.save(userAuthProvider);
        }
    }

    private UserAuthProvider toUserAuthProvider(User user, String subject) {
        return new UserAuthProvider()
                .userId(user.getId())
                .provider(GOOGLE)
                .providerUserId(subject)
                .createdAt(now());
    }

    private LoginWithGoogleResponse registrationRequired(String name, String email, String subject) {
        return new LoginWithGoogleResponse()
                .status(REGISTRATION_REQUIRED)
                .registrationToken(getRegistrationToken(email, subject))
                .suggestedName(name)
                .suggestedEmail(email);
    }

    private String getRegistrationToken(String email, String subject) {
        return jwtService.issueRegistrationToken(subject, email);
    }

    private void throwIfInvalid(User user) {
        checker.check(user, RULE_0004);
    }

    private LoginWithGoogleResponse login(User user, String subject) {
        return new LoginWithGoogleResponse()
                .status(AUTHENTICATED)
                .user(user)
                .accessToken(getAccessToken(user, subject));
    }

    private String getAccessToken(User user, String subject) {
        return jwtService.issueAccessToken(subject, user.getEmail());
    }

}
