package fr.strivestake.user.criteria;


import fr.strivestake.auth.model.ProviderEnum;
import fr.strivestake.user.model.AccountStatusEnum;

public class UserCriteria {

    private String email;
    private ProviderEnum provider;
    private String subject;
    private AccountStatusEnum accountStatus;

    public UserCriteria email(String email) {
        this.email = email;
        return this;
    }

    public UserCriteria provider(ProviderEnum provider) {
        this.provider = provider;
        return this;
    }

    public UserCriteria subject(String subject) {
        this.subject = subject;
        return this;
    }

    public UserCriteria accountStatus(AccountStatusEnum accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }
}
