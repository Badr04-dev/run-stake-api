package fr.strivestake.club.rules;

import fr.strivestake.club.model.Club;
import fr.strivestake.common.checker.RuleChecker;
import fr.strivestake.common.checker.validator.RuleValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClubChecker extends RuleChecker<Club> {
    public ClubChecker(List<RuleValidator<Club>> ruleValidators) {
        super(ruleValidators);
    }
}
