package fr.strivestake.user_auth_provider.repository;

import fr.strivestake.user_auth_provider.criteria.UserAuthProviderCriteria;
import fr.strivestake.user_auth_provider.model.ProviderEnum;
import fr.strivestake.user_auth_provider.model.UserAuthProvider;

import java.util.Optional;

public interface UserAuthProviderRepository {

    Optional<UserAuthProvider> findUserByProviderAndSubject(ProviderEnum provider, String subject);
    boolean exists(UserAuthProviderCriteria criteria);
    UserAuthProvider save(UserAuthProvider userAuthProvider);

}
