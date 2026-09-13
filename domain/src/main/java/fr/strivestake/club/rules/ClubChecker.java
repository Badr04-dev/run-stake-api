package fr.strivestake.club.rules;

import fr.strivestake.club.model.Club;
import fr.strivestake.club.rules.validators.RuleValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClubChecker extends RuleChecker<Club> {
    public ClubChecker(List<RuleValidator<Club>> ruleValidators) {
        super(ruleValidators);
    }
}
