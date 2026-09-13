package fr.strivestake.club.rules.validators;

import fr.strivestake.club.rules.RuleException;
import fr.strivestake.club.rules.Rules;

public interface RuleValidator<T> {

    default boolean isApplicable(T object) {
        return true;
    }

    default void validationFailed() {
        throw new RuleException(getRule().getValue());
    }

    void validate(T object) throws RuleException;
    Rules getRule();

}
