package fr.strivestake.auth.google.dto;

import fr.strivestake.auth.model.AuthStatusEnum;

public record RegisterWithGoogleResponseDto(
        AuthStatusEnum status,
        String accessToken,
        Long userId
)
{}
