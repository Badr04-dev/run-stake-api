package fr.strivestake.club.dao;

import fr.strivestake.club.entity.ClubEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClubCrudDao extends CrudRepository<ClubEntity, Integer> {
}
