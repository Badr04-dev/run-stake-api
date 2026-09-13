package fr.strivestake.club.entity;

import jakarta.persistence.*;
import lombok.Getter;
import fr.strivestake.stadium.entity.StadiumEntity;
import fr.strivestake.staff.entity.CoachEntity;

import java.util.Objects;

@Table(name = "CLUB")
@Entity
@Getter
public class ClubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CLUB_NAME")
    private String clubName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_COACH_ID")
    private CoachEntity coachId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_STADIUM_NAME")
    private StadiumEntity stadiumName;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ClubEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
