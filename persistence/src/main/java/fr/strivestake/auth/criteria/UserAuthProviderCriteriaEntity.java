package fr.strivestake.auth.criteria;

import fr.groupebpce.sepia.criteria.Criteria;
import fr.strivestake.auth.model.ProviderEnum;
import lombok.Getter;

@Getter
public class UserAuthProviderCriteriaEntity extends Criteria {

    private ProviderEnum provider;
    private String subject;

    public UserAuthProviderCriteriaEntity subject(String subject) {
        this.subject = subject;
        return this;
    }

    public UserAuthProviderCriteriaEntity provider(ProviderEnum provider) {
        this.provider = provider;
        return this;
    }
}
