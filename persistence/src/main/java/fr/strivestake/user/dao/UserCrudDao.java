package fr.strivestake.user.dao;

import fr.strivestake.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudDao extends CrudRepository<UserEntity, Long> {
}
