package src.sms.exception;

/**
 * Custom exception for database-related errors.
 * Optional but professional.
 */
public class DataAccessException extends RuntimeException {

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
