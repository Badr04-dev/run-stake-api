package fr.strivestake.auth.google.dto;

import fr.strivestake.common.auth.model.AuthStatusEnum;
import fr.strivestake.user.model.User;

public record LoginWithGoogleResponseDto (
        AuthStatusEnum status,

        // Response 1
        String accessToken,
        User user,

        // Response 2
        String registrationToken,
        String suggestedName,
        String suggestedEmail
) {}
