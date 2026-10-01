package fr.strivestake.auth.service;

import fr.strivestake.auth.model.UserAuthProvider;
import fr.strivestake.auth.repository.UserAuthProviderRepository;
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
