package laura.task;

import laura.Date;
import laura.exception.LauraException;

/**
 * A Task with a start and end date
 */
public class EventTask extends Task {
    final Date from;
    final Date to;

    /**
     * @param description The Task's name
     * @param from The start date of the Task
     * @param to The end date of the Task
     * @throws LauraException If any of the dates is in an incorrect format
     */
    public EventTask(String description, String from, String to) throws LauraException {
        super(description);
        this.from = new Date(from);
        this.to = new Date(to);
    }

    /**
     * @param isDone Whether the task is marked or not
     * @param description The Task's name
     * @param from The start date of the Task
     * @param to The end date of the Task
     * @throws LauraException If any of the dates is in an incorrect format
     */
    public EventTask(boolean isDone, String description, String from, String to) throws LauraException {
        super(isDone, description);
        this.from = new Date(from);
        this.to = new Date(to);
    }

    @Override
    public String encode() {
        return "E|" + super.encode() + "|" + this.from.encode() + "|" + this.to.encode();
    }

    @Override
    public String toString() {
        String timing = " (from: " + this.from + " to: " + this.to + ")";
        return "[E]" + super.toString() + timing;
    }

}
