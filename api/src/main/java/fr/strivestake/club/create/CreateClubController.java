package fr.strivestake.club.create;

import fr.strivestake.club.create.dto.CreateClubRequestDto;
import fr.strivestake.club.model.Club;
import fr.strivestake.club.rules.RuleException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("api/create")
@RequiredArgsConstructor
public class CreateClubController {

    private final CreateClubUseCase createClubUseCase;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody CreateClubRequestDto createClubRequestDto) throws RuleException {
        Club club = mapper.map(createClubRequestDto, Club.class);
        Club createdClub = createClubUseCase.create(club);
        return ResponseEntity.ok(createdClub) ;
    }

}
