package fr.strivestake.google.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import fr.strivestake.google.exception.InvalidGoogleTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static java.util.Objects.isNull;

@Service
public class GoogleTokenVerifierService {

    private final GoogleIdTokenVerifier verifier;

    public GoogleTokenVerifierService(@Value("${google.client-id}") String googleClientId) {
        this.verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(googleClientId))
                .build();
    }

    public GoogleIdToken.Payload verify(String idTokenString) {
        GoogleIdToken idToken = throwIfIdTokenIsInvalid(idTokenString);

        throwIfIdTokenIsNull(idToken);

        return idToken.getPayload();
    }

    private GoogleIdToken throwIfIdTokenIsInvalid(String idTokenString) {
        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(idTokenString);
        } catch (Exception e) {
            throw new InvalidGoogleTokenException(e);
        }
        return idToken;
    }

    private void throwIfIdTokenIsNull(GoogleIdToken idToken) {
        if (isNull(idToken)) {
            throw new InvalidGoogleTokenException();
        }
    }
}