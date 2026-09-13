package fr.strivestake.club.rules.validators;

import fr.strivestake.club.model.Club;
import fr.strivestake.club.rules.RuleException;
import fr.strivestake.club.rules.Rules;
import org.springframework.stereotype.Component;

import static fr.strivestake.club.rules.ClubRules.RULE_001;

@Component
public class Club001Validator implements RuleValidator<Club> {

    @Override
    public void validate(Club club) throws RuleException {
        if (isClubNameShort(club)) {
            validationFailed();
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
