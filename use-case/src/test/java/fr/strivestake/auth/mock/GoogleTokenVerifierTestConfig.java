package fr.strivestake.auth.mock;

import fr.strivestake.google.service.GoogleTokenVerifierService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class GoogleTokenVerifierTestConfig {

    @Bean
    @Primary
    public GoogleTokenVerifierService googleTokenVerifierService() {
        return Mockito.mock(GoogleTokenVerifierService.class);
    }
}
