package exception;

/**
 * @author rajuraghuwanshi
 */
public class InValidFileTypeException extends RuntimeException {
    public InValidFileTypeException() {
    }

    public InValidFileTypeException(String message) {
        super(message);
    }

    public InValidFileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InValidFileTypeException(Throwable cause) {
        super(cause);
    }

}
