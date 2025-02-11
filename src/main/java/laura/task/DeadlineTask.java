package laura.task;

import laura.Date;
import laura.exception.LauraException;

/**
 * A Task with an End date
 */
public class DeadlineTask extends Task {
    /** The endDate */
    private final Date deadline;

    /**
     * @param description The Task's name
     * @param deadline The date which the Task will end
     * @throws LauraException When the date is in an incorrect format
     */
    public DeadlineTask(String description, String deadline) throws LauraException {
        super(description);
        this.deadline = new Date(deadline);
    }

    /**
     * @param isDone Whether the task is marked or not
     * @param description The Task's Name
     * @param tag The tag of the Task
     * @param deadline When the Task will end
     * @throws LauraException When the date is in an incorrect format
     */
    public DeadlineTask(boolean isDone, String description, String tag, String deadline) throws LauraException {
        super(isDone, description, tag);
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
