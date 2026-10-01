package fr.strivestake.auth.google.dto;

public record RegisterUserWithGoogleRequestDto(String registrationToken, String username) {
}
