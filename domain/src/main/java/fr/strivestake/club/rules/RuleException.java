package fr.strivestake.club.rules;

public class RuleException extends RuntimeException {
    public RuleException(String rule) {
        super("Rule " + rule + " is violated.");
    }
}
