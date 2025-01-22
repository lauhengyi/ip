public class LauraException extends Exception {

    public LauraException() {
        super("Oops, I don't understand this command!");
    }

    public LauraException(String message) {
        super(message);
    }

}
