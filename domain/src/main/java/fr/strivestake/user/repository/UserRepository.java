package fr.strivestake.user.repository;

import fr.strivestake.user.criteria.UserCriteria;
import fr.strivestake.user.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByEmail(String email);
    boolean exists(UserCriteria criteria);
    User save(User user);

}
