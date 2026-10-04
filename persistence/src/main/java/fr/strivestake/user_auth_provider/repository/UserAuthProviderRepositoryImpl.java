package fr.strivestake.user_auth_provider.repository;

import fr.strivestake.user_auth_provider.criteria.UserAuthProviderCriteria;
import fr.strivestake.user_auth_provider.criteria.UserAuthProviderCriteriaEntity;
import fr.strivestake.user_auth_provider.dao.UserAuthProviderCrudDao;
import fr.strivestake.user_auth_provider.dao.UserAuthProviderQueryDao;
import fr.strivestake.user_auth_provider.entity.UserAuthProviderEntity;
import fr.strivestake.user_auth_provider.model.ProviderEnum;
import fr.strivestake.user_auth_provider.model.UserAuthProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserAuthProviderRepositoryImpl implements UserAuthProviderRepository {

    private final UserAuthProviderQueryDao queryDao;
    private final UserAuthProviderCrudDao crudDao;
    private final ModelMapper modelMapper;

    @Override
    public Optional<UserAuthProvider> findUserByProviderAndSubject(ProviderEnum provider, String subject) {
        Optional<UserAuthProviderEntity> userAuthProviderEntity = queryDao.searchOne(
                new UserAuthProviderCriteriaEntity()
                        .provider(provider)
                        .subject(subject)
        );
        return toModel(userAuthProviderEntity);
    }

    @Override
    public boolean exists(UserAuthProviderCriteria criteria) {
        return queryDao.exists(toEntity(criteria));
    }

    @Override
    public UserAuthProvider save(UserAuthProvider userAuthProvider) {
        UserAuthProviderEntity userAuthProviderEntity = crudDao.save(toEntity(userAuthProvider));
        return toModel(userAuthProviderEntity);
    }

    private UserAuthProvider toModel(UserAuthProviderEntity userAuthProviderEntity) {
        return modelMapper.map(userAuthProviderEntity, UserAuthProvider.class);
    }

    private Optional<UserAuthProvider> toModel(Optional<UserAuthProviderEntity> userEntity) {
        return userEntity.map(entity -> modelMapper.map(entity, UserAuthProvider.class));
    }

    private UserAuthProviderEntity toEntity(UserAuthProvider userAuthProvider) {
        return modelMapper.map(userAuthProvider, UserAuthProviderEntity.class);
    }

    private UserAuthProviderCriteriaEntity toEntity(UserAuthProviderCriteria userAuthProvider) {
        return modelMapper.map(userAuthProvider, UserAuthProviderCriteriaEntity.class);
    }

}
