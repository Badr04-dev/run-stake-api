package fr.strivestake.club.rules;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClubRules implements Rules {

    RULE_001("br.001.create.club");

    private final String value;

}
