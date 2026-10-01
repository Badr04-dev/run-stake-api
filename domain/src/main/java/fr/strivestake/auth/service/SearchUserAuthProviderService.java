package fr.strivestake.auth.service;

import fr.strivestake.auth.criteria.UserAuthProviderCriteria;
import fr.strivestake.auth.model.ProviderEnum;
import fr.strivestake.auth.model.UserAuthProvider;
import fr.strivestake.auth.repository.UserAuthProviderRepository;
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
