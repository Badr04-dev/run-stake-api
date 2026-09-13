package fr.strivestake.stadium.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Stadium {

    private String name;
    private String city;
    private String country;
    private int numberOfSeats;

    public Stadium name(String name) {
        this.name = name;
        return this;
    }

    public Stadium city(String city) {
        this.city = city;
        return this;
    }

    public Stadium country(String country) {
        this.country = country;
        return this;
    }

    public Stadium numberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Stadium stadium)) return false;
        return Objects.equals(name, stadium.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
