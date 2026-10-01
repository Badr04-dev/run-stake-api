package fr.strivestake.user.rules;

import fr.strivestake.common.checker.Rules;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRules implements Rules {

    RULE_0004("br.0004.auth.user"),
    RULE_002("br.002.auth.user");

    private final String value;

}
