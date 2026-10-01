package fr.strivestake.auth.repository;

import fr.strivestake.auth.criteria.UserAuthProviderCriteria;
import fr.strivestake.auth.model.ProviderEnum;
import fr.strivestake.auth.model.UserAuthProvider;

import java.util.Optional;

public interface UserAuthProviderRepository {

    Optional<UserAuthProvider> findUserByProviderAndSubject(ProviderEnum provider, String subject);
    boolean exists(UserAuthProviderCriteria criteria);
    UserAuthProvider save(UserAuthProvider userAuthProvider);

}
