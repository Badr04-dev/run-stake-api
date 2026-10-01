package fr.strivestake.user.repository;

import fr.strivestake.user.criteria.UserCriteria;
import fr.strivestake.user.criteria.UserCriteriaEntity;
import fr.strivestake.user.dao.UserCrudDao;
import fr.strivestake.user.dao.UserQueryDao;
import fr.strivestake.user.entity.UserEntity;
import fr.strivestake.user.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserQueryDao queryDao;
    private final UserCrudDao crudDao;
    private final ModelMapper modelMapper;

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<UserEntity> userEntity = queryDao.searchOne(new UserCriteriaEntity().email(email));
        return toModel(userEntity);
    }

    @Override
    public boolean exists(UserCriteria criteria) {
        return queryDao.exists(toEntity(criteria));
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = crudDao.save(toEntity(user));
        return toModel(userEntity);
    }

    private Optional<User> toModel(Optional<UserEntity> userEntity) {
        return userEntity.map(entity -> modelMapper.map(entity, User.class));
    }

    private User toModel(UserEntity userEntity) {
        return modelMapper.map(userEntity, User.class);
    }

    private UserCriteriaEntity toEntity(UserCriteria userCriteria) {
        return modelMapper.map(userCriteria, UserCriteriaEntity.class);
    }

    private UserEntity toEntity(User user) {
        return modelMapper.map(user, UserEntity.class);
    }
}
