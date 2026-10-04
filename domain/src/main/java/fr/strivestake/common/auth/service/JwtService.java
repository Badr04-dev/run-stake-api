package fr.strivestake.common.auth.service;

import fr.strivestake.auth.model.AuthStatusEnum;
import fr.strivestake.google.exception.InvalidTokenException;
import fr.strivestake.google.model.AccessTokenClaims;
import fr.strivestake.google.model.RegistrationClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.HOURS;
import static fr.strivestake.auth.model.AuthStatusEnum.REGISTRATION_REQUIRED;


@Service
public class JwtService {

    private final SecretKey key;
    private final long registrationTokenTtlMinutes = 10;
    private final long accessTokenTtlHours = 24;

    public JwtService(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String issueRegistrationToken(String googleSub, String email) {
        return Jwts.builder()
                .claim("googleSub", googleSub)
                .claim("email", email)
                .claim("purpose", REGISTRATION_REQUIRED)
                .issuedAt(new Date())
                .expiration(Date.from(now().plus(registrationTokenTtlMinutes, MINUTES)))
                .signWith(key)
                .compact();
    }

    public String issueAccessToken(String googleSub, String email) {
        return Jwts.builder()
                .subject(googleSub)
                .claim("email", email)
                .issuedAt(new Date())
                .expiration(Date.from(now().plus(accessTokenTtlHours, HOURS)))
                .signWith(key)
                .compact();
    }

    public RegistrationClaims parseRegistrationToken(String token) {
        Claims claims = Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token).getPayload();

        throwIfThePurposeIsNotToRegister(claims);

        return new RegistrationClaims(claimGoogleSub(claims), claimEmail(claims));
    }

    public AccessTokenClaims parseAccessToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().verifyWith(key).build()
                    .parseSignedClaims(token).getPayload();
        } catch (JwtException e) {
            throw new InvalidTokenException(e);
        }

        return new AccessTokenClaims(claims.getSubject(), claims.get("email", String.class));
    }

    private void throwIfThePurposeIsNotToRegister(Claims claims) {
        AuthStatusEnum status = getStatusFromClaims(claims);
        if (REGISTRATION_REQUIRED != status) {
            throw new InvalidTokenException();
        }
    }

    private @NonNull AuthStatusEnum getStatusFromClaims(Claims claims) {
        String purpose = claims.get("purpose", String.class);
        AuthStatusEnum status = AuthStatusEnum.valueOf(purpose);
        return status;
    }

    private String claimGoogleSub(Claims claims) {
        return claims.get("googleSub", String.class);
    }

    private String claimEmail(Claims claims) {
        return claims.get("email", String.class);
    }

}
