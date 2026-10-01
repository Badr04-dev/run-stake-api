package fr.strivestake.auth.dao;

import fr.strivestake.auth.entity.UserAuthProviderEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthProviderCrudDao extends CrudRepository<UserAuthProviderEntity, Long> {
}
