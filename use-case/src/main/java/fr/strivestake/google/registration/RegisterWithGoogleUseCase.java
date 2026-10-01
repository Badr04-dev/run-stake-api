package fr.strivestake.google.registration;

import fr.strivestake.auth.model.UserAuthProvider;
import fr.strivestake.auth.repository.UserAuthProviderRepository;
import fr.strivestake.google.model.RegistrationClaims;
import fr.strivestake.google.registration.model.RegisterWithGoogleRequest;
import fr.strivestake.google.registration.model.RegisterWithGoogleResponse;
import fr.strivestake.common.auth.service.JwtService;
import fr.strivestake.user.model.User;
import fr.strivestake.user.repository.UserRepository;
import fr.strivestake.user.rules.UserChecker;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static fr.strivestake.auth.model.ProviderEnum.GOOGLE;
import static fr.strivestake.user.rules.UserRules.RULE_002;
import static fr.strivestake.auth.model.AuthStatusEnum.REGISTERED;

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
        User savedUser = registerUser(newUser, claims);

        return toResponse(claims, savedUser);
    }

    private User toUser(RegisterWithGoogleRequest request, RegistrationClaims claims) {
        return new User()
                .username(request.getUsername())
                .email(claims.email());
    }

    private void throwIfInvalid(User user) {
        checker.check(user, RULE_002);
    }

    private @NonNull User registerUser(User newUser, RegistrationClaims claims) {
        User savedUser = userRepository.save(newUser);
        UserAuthProvider userAuthProvider = toUserAuthProvider(savedUser, claims);
        userAuthProviderRepository.save(userAuthProvider);
        return savedUser;
    }

    private UserAuthProvider toUserAuthProvider(User savedUser, RegistrationClaims claims) {
        return new UserAuthProvider()
                .userId(savedUser.getId())
                .provider(GOOGLE)
                .providerUserId(claims.googleSub());
    }

    private RegisterWithGoogleResponse toResponse(RegistrationClaims claims, User savedUser) {
        return new RegisterWithGoogleResponse()
                .status(REGISTERED)
                .accessToken(getAccessToken(claims))
                .userId(savedUser.getId());
    }

    private String getAccessToken(RegistrationClaims claims) {
        return jwtService.issueAccessToken(claims.googleSub(), claims.email());
    }

}
