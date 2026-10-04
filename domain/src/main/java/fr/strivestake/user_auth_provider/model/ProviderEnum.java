package fr.strivestake.user_auth_provider.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProviderEnum {

    GOOGLE("GOOGLE"),
    APPLE("APPLE");

    private final String value;
}
