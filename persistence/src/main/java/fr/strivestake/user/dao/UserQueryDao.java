package fr.strivestake.user.dao;

import fr.strivestake.user.criteria.UserCriteriaEntity;
import fr.strivestake.user.entity.UserEntity;
import fr.groupebpce.sepia.dao.QueryContext;
import fr.groupebpce.sepia.dao.QueryDao;
import org.springframework.stereotype.Repository;

import static java.util.Objects.nonNull;

@Repository
public class UserQueryDao extends QueryDao<UserEntity, UserCriteriaEntity> {

    protected UserQueryDao() {
        super(UserEntity.class);
    }

    @Override
    protected void filter(QueryContext<UserCriteriaEntity> context) {

        UserCriteriaEntity criteria = context.getCriteria();

        if(nonNull(criteria.getEmail())) {
            context.addCondition("user.email = :email")
                    .addParameter("email", criteria.getEmail());
        }

        if(nonNull(criteria.getUsername())) {
            context.addCondition("user.username = :username")
                    .addParameter("username", criteria.getUsername());
        }

        if(nonNull(criteria.getAccountStatus())) {
            context.addCondition("user.accountStatus = :accountStatus")
                    .addParameter("accountStatus", criteria.getAccountStatus());
        }

    }

    @Override
    protected String getAliasName() {
        return "user";
    }
}
