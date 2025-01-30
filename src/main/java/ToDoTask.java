public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    public ToDoTask(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String encode() {
        return "T|" + super.encode();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
