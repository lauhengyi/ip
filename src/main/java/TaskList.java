import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(String description) {
        this.list.add(new Task(description));
        Message.send("Added: " + description);
    }

    public void mark(int index) {
        if (index > this.list.size() || index < 1) {
            Message.send("Sorry, that task does not exist!");
            return;
        }
        Task curr = this.list.get(index - 1);
        curr.mark();
        Message.send("Nice! I've marked this task as done:\n" + curr);
    }

    public void unmark(int index) {
        if (index > this.list.size() || index < 1) {
            Message.send("Sorry, that task does not exist!");
            return;
        }
        Task curr = this.list.get(index - 1);
        curr.unmark();
        Message.send("Ok! I've marked this task as not done:\n" + curr);
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            message.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return message.toString();
    }
}
