package fr.strivestake.staff.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Entity
@Table(name = "COACH")
@Getter
public class CoachEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "YEARS_OF_EXPERIENCE")
    private int yearsOfExperience;

    public CoachEntity id(Long id) {
        this.id = id;
        return this;
    }

    public CoachEntity firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CoachEntity lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CoachEntity yearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CoachEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
