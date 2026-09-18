package fr.strivestake.club.create.model;

import fr.strivestake.stadium.model.Stadium;
import fr.strivestake.staff.model.Coach;
import lombok.Getter;
import org.jspecify.annotations.NonNull;

@Getter
public class CreateClubRequest {

    @NonNull
    private String clubName;
    private Coach coach;
    private Stadium stadium;

    public CreateClubRequest clubName(@NonNull String clubName) {
        this.clubName = clubName;
        return this;
    }

    public CreateClubRequest coach(Coach coach) {
        this.coach = coach;
        return this;
    }

    public CreateClubRequest stadium(Stadium stadium) {
        this.stadium = stadium;
        return this;
    }
}
