package fr.strivestake.google.exception;

public class InvalidGoogleTokenException extends RuntimeException {

    public InvalidGoogleTokenException() {
        super("Invalid or expired Google token");
    }

    public InvalidGoogleTokenException(Throwable cause) {
        super("Invalid or expired Google token", cause);
    }

}
