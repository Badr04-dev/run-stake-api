package fr.strivestake.club.service;

import fr.strivestake.club.model.Club;
import fr.strivestake.club.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateClubService {

    private final ClubRepository clubRepository;

    public Club save(Club club) {
        return clubRepository.save(club);
    }

}
