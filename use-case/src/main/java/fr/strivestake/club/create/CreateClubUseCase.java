package fr.strivestake.club.create;

import fr.strivestake.club.create.model.CreateClubRequest;
import fr.strivestake.club.rules.ClubChecker;
import fr.strivestake.club.rules.RuleException;
import fr.strivestake.club.service.CreateClubService;
import fr.strivestake.club.model.Club;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static fr.strivestake.club.rules.ClubRules.RULE_001;

@Transactional
@Component
@RequiredArgsConstructor
public class CreateClubUseCase {

    private final ClubChecker checker;
    private final CreateClubService createClubService;

    public Club create(CreateClubRequest request) throws RuleException {
        Club club = createClub(request);
        throwIfInvalid(club);
        return createClubService.save(club);
    }

    private Club createClub(CreateClubRequest request) {
        return new Club().clubName(request.getClubName())
                .coach(request.getCoach())
                .stadium(request.getStadium());
    }

    private void throwIfInvalid(Club club) {
        checker.check(club, RULE_001);
    }

}
