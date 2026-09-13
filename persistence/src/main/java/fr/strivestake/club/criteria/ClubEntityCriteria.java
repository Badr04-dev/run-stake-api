package fr.strivestake.club.criteria;

import fr.groupebpce.sepia.criteria.Criteria;
import lombok.Getter;
import fr.strivestake.staff.entity.CoachEntity;

@Getter
public class ClubEntityCriteria extends Criteria {

    private String clubName;
    private CoachEntity coach;

    public ClubEntityCriteria clubName(String clubName) {
        this.clubName = clubName;
        return this;
    }

    public ClubEntityCriteria coach(CoachEntity coach) {
        this.coach = coach;
        return this;
    }
}
