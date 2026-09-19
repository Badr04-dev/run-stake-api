package fr.strivestake.common.checker;

import fr.strivestake.common.checker.validator.RuleValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class RuleChecker<T> {

    private final List<RuleValidator<T>> validators;

    public void check(T object, Rules... rules) throws RuleException {
        validators.stream()
                .filter(validator -> Arrays.asList(rules).contains(validator.getRule()))
                .filter(validator -> validator.isApplicable(object))
                .forEach(validator -> validator.validate(object));
    }

}
