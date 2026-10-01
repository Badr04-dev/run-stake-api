package fr.strivestake.auth.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthStatusEnum {

    AUTHENTICATED("AUTHENTICATED"),
    REGISTRATION_REQUIRED("REGISTRATION_REQUIRED"),
    REGISTERED("REGISTERED");

    private final String value;
}
