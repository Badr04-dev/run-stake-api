package fr.strivestake.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountStatusEnum {

    BANNED("BANNED"),
    ACTIVE("ACTIVE");

    private final String value;

}
