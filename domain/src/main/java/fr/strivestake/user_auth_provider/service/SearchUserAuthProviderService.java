package fr.strivestake.user_auth_provider.service;

import fr.strivestake.user_auth_provider.criteria.UserAuthProviderCriteria;
import fr.strivestake.user_auth_provider.model.ProviderEnum;
import fr.strivestake.user_auth_provider.model.UserAuthProvider;
import fr.strivestake.user_auth_provider.repository.UserAuthProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchUserAuthProviderService {

    private final UserAuthProviderRepository userAuthProviderRepository;

    public Optional<UserAuthProvider> findUserByProviderAndSubject(ProviderEnum provider, String subject) {
        return userAuthProviderRepository.findUserByProviderAndSubject(provider, subject);
    }

    public boolean hasSameAuthProvider(ProviderEnum provider, String subject) {
        return userAuthProviderRepository.exists(new UserAuthProviderCriteria().provider(provider).subject(subject));
    }

    public boolean hasNotSameAuthProvider(ProviderEnum provider, String subject) {
        return !hasSameAuthProvider(provider, subject);
    }

}
