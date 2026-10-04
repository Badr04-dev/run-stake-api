package fr.strivestake.auth.google.dto;

import fr.strivestake.auth.model.AuthStatusEnum;
import fr.strivestake.user.dto.UserDto;

public record RegisterWithGoogleResponseDto(
        AuthStatusEnum status,
        String accessToken,
        UserDto user
)
{}
