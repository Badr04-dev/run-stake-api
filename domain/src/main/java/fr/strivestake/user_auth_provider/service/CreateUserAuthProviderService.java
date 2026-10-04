package fr.strivestake.user_auth_provider.service;

import fr.strivestake.user_auth_provider.model.UserAuthProvider;
import fr.strivestake.user_auth_provider.repository.UserAuthProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserAuthProviderService {

    private final UserAuthProviderRepository userAuthProviderRepository;

    public UserAuthProvider save(UserAuthProvider userAuthProvider) {
        return userAuthProviderRepository.save(userAuthProvider);
    }

}
