package fr.strivestake.common.auth.exception;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("Invalid or expired token");
    }

    public InvalidTokenException(Throwable cause) {
        super("Invalid or expired token", cause);
    }
}
