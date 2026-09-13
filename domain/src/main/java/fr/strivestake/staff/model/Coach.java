package fr.strivestake.staff.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Coach {

    private Long id;
    private String firstName;
    private String lastName;
    private int yearsOfExperience;

    public Coach id(Long id) {
        this.id = id;
        return this;
    }

    public Coach firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Coach lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Coach yearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coach coach)) return false;
        return Objects.equals(id, coach.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
