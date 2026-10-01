package fr.strivestake.auth.google;

import fr.strivestake.auth.google.dto.RegisterUserWithGoogleRequestDto;
import fr.strivestake.auth.google.dto.RegisterWithGoogleResponseDto;
import fr.strivestake.auth.google.dto.LoginWithGoogleRequestDto;
import fr.strivestake.auth.google.dto.LoginWithGoogleResponseDto;
import fr.strivestake.google.login.LoginWithGoogleUseCase;
import fr.strivestake.google.login.model.LoginWithGoogleRequest;
import fr.strivestake.google.login.model.LoginWithGoogleResponse;
import fr.strivestake.common.checker.RuleException;
import fr.strivestake.google.registration.RegisterWithGoogleUseCase;
import fr.strivestake.google.registration.model.RegisterWithGoogleRequest;
import fr.strivestake.google.registration.model.RegisterWithGoogleResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/google")
@RequiredArgsConstructor
public class AuthentificationWithGoogleController {

    private final LoginWithGoogleUseCase loginWithGoogleUseCase;
    private final RegisterWithGoogleUseCase registerWithGoogleUseCase;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<LoginWithGoogleResponseDto> authenticateWithGoogle(@RequestBody LoginWithGoogleRequestDto loginWithGoogleRequestDto) throws RuleException {
        LoginWithGoogleRequest request = mapper.map(loginWithGoogleRequestDto, LoginWithGoogleRequest.class);
        LoginWithGoogleResponse response = loginWithGoogleUseCase.login(request);
        LoginWithGoogleResponseDto responseDto = mapper.map(response, LoginWithGoogleResponseDto.class);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("complete-registration")
    public ResponseEntity<RegisterWithGoogleResponseDto> registerUserWithGoogle(@RequestBody RegisterUserWithGoogleRequestDto requestDto) throws RuleException {
        RegisterWithGoogleRequest request = mapper.map(requestDto, RegisterWithGoogleRequest.class);
        RegisterWithGoogleResponse response = registerWithGoogleUseCase.register(request);
        RegisterWithGoogleResponseDto responseDto = mapper.map(response, RegisterWithGoogleResponseDto.class);
        return ResponseEntity.ok(responseDto);
    }

}
