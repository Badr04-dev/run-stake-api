package fr.strivestake.auth.common;

import fr.strivestake.auth.dao.UserAuthProviderCrudDao;
import fr.strivestake.auth.entity.UserAuthProviderEntity;
import fr.strivestake.user.dao.UserCrudDao;
import fr.strivestake.user.entity.UserEntity;
import fr.strivestake.user.model.AccountStatusEnum;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import static fr.strivestake.auth.model.ProviderEnum.GOOGLE;
import static java.time.LocalDateTime.now;

@Getter
public class InitializeDbStepDef {

    @Autowired
    private UserCrudDao userCrudDao;

    @Autowired
    private UserAuthProviderCrudDao userAuthProviderCrudDao;

    private UserEntity existingUserInDb;
    private UserAuthProviderEntity existingUserAuthProviderInDb;

    @Before
    public void setUp() {
        userAuthProviderCrudDao.deleteAll();
        userCrudDao.deleteAll();
    }

    @Given("a user {string} with email {string}, provider user id {string} and has an account status of {string} exists in the database")
    public void aUserExists(String username, String email, String providerUserId, String accountStatus) {
        existingUserInDb = new UserEntity()
                .username(username)
                .email(email)
                .accountStatus(AccountStatusEnum.valueOf(accountStatus))
                .createdAt(now());
        userCrudDao.save(existingUserInDb);

        existingUserAuthProviderInDb = new UserAuthProviderEntity()
                .user(existingUserInDb)
                .provider(GOOGLE)
                .providerUserId(providerUserId)
                .createdAt(now());
        userAuthProviderCrudDao.save(existingUserAuthProviderInDb);
    }

}
