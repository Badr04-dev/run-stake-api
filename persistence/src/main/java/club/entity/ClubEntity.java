package club.entity;

import jakarta.persistence.*;

@Table(name = "CLUB")
@Entity
public class ClubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "club_name")
    private String clubName;

    @Column(name = "coach_id")
    private Long coachId;

    @Column(name = "stadium_name")
    private String stadiumName;

}
