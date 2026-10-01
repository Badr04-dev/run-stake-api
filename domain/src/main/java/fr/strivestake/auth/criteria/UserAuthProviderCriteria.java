package fr.strivestake.auth.criteria;

import fr.strivestake.auth.model.ProviderEnum;

public class UserAuthProviderCriteria {

    private ProviderEnum provider;
    private String subject;

    public UserAuthProviderCriteria provider(ProviderEnum provider) {
        this.provider = provider;
        return this;
    }

    public UserAuthProviderCriteria subject(String subject) {
        this.subject = subject;
        return this;
    }
}
