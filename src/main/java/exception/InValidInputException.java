package exception;

/**
 * @author rajuraghuwanshi
 */
public class InValidInputException extends RuntimeException {
    public InValidInputException() {
    }

    public InValidInputException(String message) {
        super(message);
    }

    public InValidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InValidInputException(Throwable cause) {
        super(cause);
    }

}

