package fr.strivestake.club.model;

import fr.strivestake.stadium.model.Stadium;
import fr.strivestake.staff.model.Coach;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Club {

    private Long id;
    private String clubName;
    private Coach coach;
    private Stadium stadium;

    public Club id(Long id) {
        this.id = id;
        return this;
    }

    public Club clubName(String clubName) {
        this.clubName = clubName;
        return this;
    }

    public Club coach(Coach coach) {
        this.coach = coach;
        return this;
    }

    public Club stadium(Stadium stadium) {
        this.stadium = stadium;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Club club)) return false;
        return Objects.equals(id, club.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
