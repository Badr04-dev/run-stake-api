package fr.strivestake.club.repository;

import fr.strivestake.club.dao.ClubCrudDao;
import fr.strivestake.club.dao.ClubQueryDao;
import fr.strivestake.club.entity.ClubEntity;
import fr.strivestake.club.model.Club;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClubRepositoryImpl implements ClubRepository {

    private final ClubQueryDao queryDao;
    private final ClubCrudDao crudDao;
    private final ModelMapper modelMapper;

    @Override
    public Club save(Club club) {
        ClubEntity clubEntity = modelMapper.map(club, ClubEntity.class);
        ClubEntity savedClubEntity = crudDao.save(clubEntity);
        return toModel(savedClubEntity);
    }

    @Override
    public Club findById(Long id) {
        ClubEntity clubEntity = queryDao.find(id);
        return toModel(clubEntity);
    }

    private ClubEntity toEntity(Club club) {
        return modelMapper.map(club, ClubEntity.class);
    }

    private Club toModel(ClubEntity clubEntity) {
        return modelMapper.map(clubEntity, Club.class);
    }

}
