public class DecodeException extends LauraException{
    public DecodeException() {
        super("An error occurred when loading your tasks from drive. Perhaps some of your tasks contain the '|' character?");
    }
}
