package fr.strivestake.user_auth_provider.dao;

import fr.strivestake.user_auth_provider.entity.UserAuthProviderEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthProviderCrudDao extends CrudRepository<UserAuthProviderEntity, Long> {
}
