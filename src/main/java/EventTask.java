public class EventTask extends Task {
    final Date from;
    final Date to;

    public EventTask(String description, String from, String to) throws LauraException {
        super(description);
        this.from = new Date(from);
        this.to = new Date(to);
    }

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
