package laura.task;

/**
 * The parent for all Tasks
 */
public abstract class Task {
    private final String description;
    /**
     * Whether the Task is marked or not
     */
    private boolean isDone;

    /**
     * Create a Task instance
     * @param description The Task's Name
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Create a Task instance
     * @param isDone Whether the task is marked
     * @param description What the Task is named
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark the Task
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark the Task
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Whether the description has the keyword inside it
     * @param keyword The keyword to be searched
     * @return Whether the description has the keyword inside it
     */
    public boolean has(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Get the encoded version of the Task for storage on local
     * @return The encoded string of the Task
     */
    public String encode() {
        String encodedDone = isDone ? "1" : "0";
        return encodedDone + "|" + this.description;
    }

    @Override
    public String toString() {
        String symbol = isDone ? "[X]" : "[ ]";
        return symbol + " " + this.description;
    }
}
