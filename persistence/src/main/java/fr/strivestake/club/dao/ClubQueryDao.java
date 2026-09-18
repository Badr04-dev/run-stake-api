package fr.strivestake.club.dao;

import fr.strivestake.club.criteria.ClubEntityCriteria;
import fr.strivestake.club.entity.ClubEntity;
import fr.groupebpce.sepia.dao.QueryContext;
import fr.groupebpce.sepia.dao.QueryDao;
import org.springframework.stereotype.Repository;

import static java.util.Objects.nonNull;

@Repository
public class ClubQueryDao extends QueryDao<ClubEntity, ClubEntityCriteria> {

    protected ClubQueryDao() {
        super(ClubEntity.class);
    }

    @Override
    protected void filter(QueryContext<ClubEntityCriteria> context) {

        ClubEntityCriteria criteria = context.getCriteria();

        if(nonNull(criteria.getClubName())) {
            context.addCondition("club.clubName = :clubName")
                    .addParameter("clubName", criteria.getClubName());
        }

        if(nonNull(criteria.getCoach())) {
            context.addCondition("club.coach = :coach")
                    .addParameter("coach", criteria.getCoach());
        }

    }

    @Override
    protected String getAliasName() {
        return "club";
    }
}
