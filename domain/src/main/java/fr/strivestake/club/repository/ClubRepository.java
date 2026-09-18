package fr.strivestake.club.repository;

import fr.strivestake.club.model.Club;

public interface ClubRepository {

    Club save(Club club);
    Club findById(Long id);

}
