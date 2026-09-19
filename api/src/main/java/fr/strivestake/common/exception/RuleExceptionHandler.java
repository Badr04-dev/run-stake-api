package fr.strivestake.common.exception;

import fr.strivestake.common.checker.RuleException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RuleExceptionHandler {

    @ExceptionHandler(RuleException.class)
    public ResponseEntity<RuleErrorResponse> handleRuleException(
            RuleException exception,
            HttpServletRequest request) {

        RuleErrorResponse response = new RuleErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Business Rule Violation",
                exception.getRuleId(),
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
