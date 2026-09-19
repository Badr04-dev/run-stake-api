package fr.strivestake.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RuleErrorResponse {

    private final int status;
    private final String error;
    private final String ruleId;
    private final String message;
    private final String path;

}
