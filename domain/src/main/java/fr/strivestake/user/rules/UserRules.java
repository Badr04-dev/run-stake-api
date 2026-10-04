package fr.strivestake.user.rules;

import fr.strivestake.common.checker.Rules;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRules implements Rules {

    RULE_0001("br.0001.auth.user"),
    RULE_0002("br.0002.auth.user"),
    RULE_0004("br.0004.auth.user");

    private final String value;

}
