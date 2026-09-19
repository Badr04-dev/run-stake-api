package fr.strivestake.club.create;

import fr.strivestake.club.create.dto.CreateClubRequestDto;
import fr.strivestake.club.create.model.CreateClubRequest;
import fr.strivestake.club.model.Club;
import fr.strivestake.common.checker.RuleException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/create")
@RequiredArgsConstructor
public class CreateClubController {

    private final CreateClubUseCase createClubUseCase;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody CreateClubRequestDto createClubRequestDto) throws RuleException {
        CreateClubRequest request = mapper.map(createClubRequestDto, CreateClubRequest.class);
        Club createdClub = createClubUseCase.create(request);
        return ResponseEntity.ok(createdClub) ;
    }

}
