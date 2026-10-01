package fr.strivestake;

import fr.strivestake.auth.mock.GoogleTokenVerifierTestConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("test")
@Import(GoogleTokenVerifierTestConfig.class)
public class CucumberSpringConfiguration {
}
