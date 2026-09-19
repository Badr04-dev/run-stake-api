package fr.strivestake.club.rules.validators;

import fr.strivestake.club.model.Club;
import fr.strivestake.common.checker.RuleException;
import fr.strivestake.common.checker.validator.RuleValidator;
import fr.strivestake.common.checker.Rules;
import org.springframework.stereotype.Component;

import static fr.strivestake.club.rules.ClubRules.RULE_001;

@Component
public class Club001Validator implements RuleValidator<Club> {

    @Override
    public void validate(Club club) throws RuleException {
        if (isClubNameShort(club)) {
            validationFailed("Club name is too short");
        }
    }

    private boolean isClubNameShort(Club club) {
        return club.getClubName().length() <= 3;
    }

    @Override
    public Rules getRule() {
        return RULE_001;
    }

}
