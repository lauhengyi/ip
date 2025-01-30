public class EventTask extends Task {
    final String from;
    final String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public EventTask(boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String encode() {
        return "E|" + super.encode() + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        String timing = " (from: " + this.from + " to: " + this.to + ")";
        return "[E]" + super.toString() + timing;
    }

}
