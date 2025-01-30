public class DeadlineTask extends Task {
    private final String deadline;
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public DeadlineTask(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String encode() {
        return "D|" + super.encode() + "|" + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

}
