package fr.strivestake.user.rules;

import fr.strivestake.user.model.User;
import fr.strivestake.common.checker.RuleChecker;
import fr.strivestake.common.checker.validator.RuleValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserChecker extends RuleChecker<User> {
    public UserChecker(List<RuleValidator<User>> ruleValidators) {
        super(ruleValidators);
    }
}
