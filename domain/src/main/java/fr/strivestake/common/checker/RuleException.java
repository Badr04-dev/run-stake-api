package fr.strivestake.common.checker;

import lombok.Getter;

@Getter
public class RuleException extends RuntimeException {

    private final String ruleId;

    public RuleException(String ruleId, String message) {
        super(message);
        this.ruleId = ruleId;
    }
}
