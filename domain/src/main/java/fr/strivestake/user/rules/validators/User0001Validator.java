package fr.strivestake.user.rules.validators;

import fr.strivestake.common.checker.RuleException;
import fr.strivestake.common.checker.Rules;
import fr.strivestake.common.checker.validator.RuleValidator;
import fr.strivestake.user.model.User;
import fr.strivestake.user.service.SearchUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static fr.strivestake.user.rules.UserRules.RULE_0001;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class User0001Validator implements RuleValidator<User> {

    private final SearchUserService searchUserService;

    @Override
    public boolean isApplicable(User user) {
        return isUsernameAndEmailNotNull(user) && searchUserService.isEmailNotExists(user.getEmail());
    }

    @Override
    public void validate(User user) throws RuleException {
        if (searchUserService.isUsernameExists(user.getUsername())) {
            validationFailed("Username already exists");
        }
    }

    private boolean isUsernameAndEmailNotNull(User user) {
        return nonNull(user.getUsername()) && nonNull(user.getEmail());
    }

    @Override
    public Rules getRule() {
        return RULE_0001;
    }

}
