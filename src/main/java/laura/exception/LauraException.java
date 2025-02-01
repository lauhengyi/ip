package laura.exception;

/**
 * The custom exception for all runtime errors that occur for Laura
 */
public class LauraException extends Exception {

    public LauraException() {
        super("Oops, I don't understand this command!");
    }

    public LauraException(String message) {
        super(message);
    }

}
