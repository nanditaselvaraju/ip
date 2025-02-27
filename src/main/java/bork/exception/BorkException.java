package bork.exception;

/**
 * Represents an exception specific to the Bork application.
 * Thrown when an error occurs during command execution or task management.
 */
public class BorkException extends Exception {
    public BorkException(String message) {
        super(message);
    }
}
