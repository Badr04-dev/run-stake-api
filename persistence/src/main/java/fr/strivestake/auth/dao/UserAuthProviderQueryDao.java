package fr.strivestake.auth.dao;

import fr.groupebpce.sepia.dao.QueryContext;
import fr.groupebpce.sepia.dao.QueryDao;
import fr.strivestake.auth.criteria.UserAuthProviderCriteriaEntity;
import fr.strivestake.auth.entity.UserAuthProviderEntity;
import org.springframework.stereotype.Repository;

import static java.util.Objects.nonNull;

@Repository
public class UserAuthProviderQueryDao extends QueryDao<UserAuthProviderEntity, UserAuthProviderCriteriaEntity> {

    protected UserAuthProviderQueryDao() {
        super(UserAuthProviderEntity.class);
    }

    @Override
    protected void filter(QueryContext<UserAuthProviderCriteriaEntity> context) {

        UserAuthProviderCriteriaEntity criteria = context.getCriteria();

        if(nonNull(criteria.getProvider())) {
            context.addCondition("userAuthProvider.provider = :provider")
                    .addParameter("provider", criteria.getProvider());
        }

        if (nonNull(criteria.getSubject())) {
            context.addCondition("userAuthProvider.providerUserId = :subject")
                    .addParameter("subject", criteria.getSubject());
        }

    }

    @Override
    protected String getAliasName() {
        return "userAuthProvider";
    }
}
