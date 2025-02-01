package laura.task;

/**
 * A basic Task
 */
public class ToDoTask extends Task {
    /**
     * Create a ToDoTask instance
     * @param description The Task's name
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Create a ToDoTask instance
     * @param isDone Whether the task is marked
     * @param description The Task's name
     */
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
