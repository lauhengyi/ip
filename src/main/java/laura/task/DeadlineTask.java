package laura.task;

import laura.Date;
import laura.exception.LauraException;

public class DeadlineTask extends Task {
    private final Date deadline;

    public DeadlineTask(String description, String deadline) throws LauraException {
        super(description);
        this.deadline = new Date(deadline);
    }

    public DeadlineTask(boolean isDone, String description, String deadline) throws LauraException {
        super(isDone, description);
        this.deadline = new Date(deadline);
    }

    @Override
    public String encode() {
        return "D|" + super.encode() + "|" + this.deadline.encode();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

}
