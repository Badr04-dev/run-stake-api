package fr.strivestake.auth.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProviderEnum {

    GOOGLE("GOOGLE"),
    APPLE("APPLE");

    private final String value;
}
