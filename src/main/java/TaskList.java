import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    final static String dataPath = "src/main/data/tasks.txt";
    ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    private void save() {
        File file = new File(TaskList.dataPath);
        String newLine = System.lineSeparator();
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : this.list) {
                writer.write(task.encode() + newLine);
            }
        } catch (IOException e) {
            return;
        }
    }

    public void add(Task task) {
        this.list.add(task);
        this.save();
        Message.send("Got it! I've added this task:\n" + task);
    }

    public void delete(int index) throws LauraException {
        if (index > this.list.size() || index < 1) {
            throw new LauraException("Sorry, that task does not exist!");
        }
        Task removed = this.list.remove(index - 1);
        this.save();
        Message.send("Noted. I've removed this task:\n"
                + removed +
                "\nNow you have " + this.list.size() + " in this list.");
    }

    public void mark(int index) throws LauraException {
        if (index > this.list.size() || index < 1) {
            throw new LauraException("Sorry, that task does not exist!");
        }
        Task curr = this.list.get(index - 1);
        curr.mark();
        this.save();
        Message.send("Nice! I've marked this task as done:\n" + curr);
    }

    public void unmark(int index) throws LauraException {
        if (index > this.list.size() || index < 1) {
            throw new LauraException("Sorry, that task does not exist!");
        }
        Task curr = this.list.get(index - 1);
        curr.unmark();
        this.save();
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
