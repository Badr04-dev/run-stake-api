package fr.strivestake.user.criteria;

import fr.groupebpce.sepia.criteria.Criteria;
import fr.strivestake.user.model.AccountStatusEnum;
import lombok.Getter;

@Getter
public class UserCriteriaEntity extends Criteria {

    private String email;
    private String username;
    private AccountStatusEnum accountStatus;

    public UserCriteriaEntity email(String email) {
        this.email = email;
        return this;
    }

    public UserCriteriaEntity username(String username) {
        this.username = username;
        return this;
    }

    public UserCriteriaEntity accountStatus(AccountStatusEnum accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }
}
