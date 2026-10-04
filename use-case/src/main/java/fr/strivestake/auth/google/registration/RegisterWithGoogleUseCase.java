package fr.strivestake.auth.google.registration;

import fr.strivestake.user_auth_provider.model.UserAuthProvider;
import fr.strivestake.user_auth_provider.repository.UserAuthProviderRepository;
import fr.strivestake.google.service.JwtService;
import fr.strivestake.google.model.RegistrationClaims;
import fr.strivestake.auth.google.registration.model.RegisterWithGoogleRequest;
import fr.strivestake.auth.google.registration.model.RegisterWithGoogleResponse;
import fr.strivestake.user.model.User;
import fr.strivestake.user.repository.UserRepository;
import fr.strivestake.user.rules.UserChecker;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static fr.strivestake.common.auth.model.AuthStatusEnum.REGISTERED;
import static fr.strivestake.user_auth_provider.model.ProviderEnum.GOOGLE;
import static fr.strivestake.user.model.AccountStatusEnum.ACTIVE;
import static fr.strivestake.user.rules.UserRules.RULE_0001;
import static fr.strivestake.user.rules.UserRules.RULE_0002;
import static java.time.LocalDateTime.now;

@Transactional
@Component
@RequiredArgsConstructor
public class RegisterWithGoogleUseCase {

    private final JwtService jwtService;
    private final UserChecker checker;
    private final UserRepository userRepository;
    private final UserAuthProviderRepository userAuthProviderRepository;

    public RegisterWithGoogleResponse register(RegisterWithGoogleRequest request) {
        RegistrationClaims claims = jwtService.parseRegistrationToken(request.getRegistrationToken());

        User newUser = toUser(request, claims);
        throwIfInvalid(newUser);
        User savedUser = registerUser(newUser);
        saveUserAuthProvider(savedUser, claims);

        return toResponse(claims, savedUser);
    }

    private User toUser(RegisterWithGoogleRequest request, RegistrationClaims claims) {
        return new User()
                .username(request.getUsername())
                .email(claims.email())
                .accountStatus(ACTIVE)
                .createdAt(now());
    }

    private void throwIfInvalid(User user) {
        checker.check(user, RULE_0001, RULE_0002);
    }

    private @NonNull User registerUser(User newUser) {
        return userRepository.save(newUser);
    }

    private void saveUserAuthProvider(User savedUser, RegistrationClaims claims) {
        UserAuthProvider userAuthProvider = toUserAuthProvider(savedUser, claims);
        userAuthProviderRepository.save(userAuthProvider);
    }

    private UserAuthProvider toUserAuthProvider(User savedUser, RegistrationClaims claims) {
        return new UserAuthProvider()
                .userId(savedUser.getId())
                .provider(GOOGLE)
                .providerUserId(claims.googleSub())
                .createdAt(now());
    }

    private RegisterWithGoogleResponse toResponse(RegistrationClaims claims, User savedUser) {
        return new RegisterWithGoogleResponse()
                .status(REGISTERED)
                .accessToken(getAccessToken(claims))
                .user(savedUser);
    }

    private String getAccessToken(RegistrationClaims claims) {
        return jwtService.issueAccessToken(claims.googleSub(), claims.email());
    }

}
