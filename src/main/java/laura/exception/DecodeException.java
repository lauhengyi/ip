package laura.exception;

/**
 * Exception for when there is an issue decoding tasks
 * from the local drive (Usually when the format is incorrect)
 */
public class DecodeException extends LauraException {
    /**
     * Creates a Decoding exception instance
     */
    public DecodeException() {
        super("An error occurred when loading your tasks from drive. "
                + "Perhaps some of your tasks contain the '|' character?");
    }
}
