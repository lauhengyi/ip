import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        this.list.add(task);
        Message.send("Got it! I've added this task:\n" + task);
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
