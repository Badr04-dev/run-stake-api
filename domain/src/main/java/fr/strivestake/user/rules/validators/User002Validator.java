package fr.strivestake.user.rules.validators;

import fr.strivestake.common.checker.RuleException;
import fr.strivestake.common.checker.Rules;
import fr.strivestake.common.checker.validator.RuleValidator;
import fr.strivestake.user.model.User;
import org.springframework.stereotype.Component;

import static fr.strivestake.user.rules.UserRules.RULE_002;
import static java.util.Objects.isNull;

@Component
public class User002Validator implements RuleValidator<User> {

    @Override
    public void validate(User user) throws RuleException {
        if (isNull(user.getUsername())) {
            validationFailed("Username is required");
        }
    }

    @Override
    public Rules getRule() {
        return RULE_002;
    }
}
