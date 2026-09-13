package fr.strivestake.club.create.dto;

import fr.strivestake.stadium.dto.StadiumDto;
import fr.strivestake.staff.dto.CoachDto;

public record CreateClubRequestDto(String clubName, CoachDto coachDto, StadiumDto stadiumDto) {}
