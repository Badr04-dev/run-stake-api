package fr.strivestake.user.dto;

import java.time.LocalDateTime;

public record UserDto (
        Long id,
        String username,
        String email,
        String accountStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
)
{}
