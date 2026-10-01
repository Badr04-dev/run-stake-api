package fr.strivestake.user.rules.validators;

import fr.strivestake.user.model.User;
import fr.strivestake.common.checker.RuleException;
import fr.strivestake.common.checker.validator.RuleValidator;
import fr.strivestake.common.checker.Rules;
import fr.strivestake.user.service.SearchUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static fr.strivestake.user.rules.UserRules.RULE_0004;

@Component
@RequiredArgsConstructor
public class User001Validator implements RuleValidator<User> {

    private final SearchUserService searchUserService;

    @Override
    public void validate(User user) throws RuleException {
        if (isUserBanned(user)) {
            validationFailed("User is banned");
        }
    }

    private boolean isUserBanned(User user) {
        return searchUserService.isUserBanned(user.getEmail());
    }

    @Override
    public Rules getRule() {
        return RULE_0004;
    }

}
