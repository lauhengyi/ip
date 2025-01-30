package laura.task;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

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
