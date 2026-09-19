package fr.strivestake.common.checker.validator;

import fr.strivestake.common.checker.RuleException;
import fr.strivestake.common.checker.Rules;

public interface RuleValidator<T> {

    default boolean isApplicable(T object) {
        return true;
    }

    default void validationFailed(String message) {
        throw new RuleException(getRule().getValue(), message);
    }

    void validate(T object) throws RuleException;
    Rules getRule();

}
