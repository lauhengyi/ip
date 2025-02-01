package laura;

import laura.exception.DecodeException;
import laura.exception.LauraException;
import laura.task.DeadlineTask;
import laura.task.EventTask;
import laura.task.Task;
import laura.task.ToDoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Holds a list of Tasks that can be manipulated with various methods
 */
public class TaskList {
    /** Where the local data should be stored */
    private final String dataPath;
    /** The list that will hold the tasks*/
    ArrayList<Task> list;

    /**
     * Create an instance of TaskList
     *
     * @param dataPath The path where the local data should
     *                 be stored (Local to the root directory)
     */
    public TaskList(String dataPath) {
        this.dataPath = dataPath;
        this.list = new ArrayList<>();
    }

    /**
     * Encode and Save the Tasklist to local
     */
    private void save() {
        File file = new File(this.dataPath);
        String newLine = System.lineSeparator();
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : this.list) {
                writer.write(task.encode() + newLine);
            }
        } catch (IOException e) {
            UI.send(e.getMessage());
        }
    }

    /**
     * Takes a line of encoded data, decodes it,
     * and returns the corresponding Task
     *
     * @param data The encoded data
     * @return The decoded Task
     * @throws LauraException When the data is not encoded in a valid format
     */
    private Task decode(String data) throws LauraException {
        String[] args = data.split(Pattern.quote("|"));
        if (args.length < 2 || !"TDE".contains(args[0]) || !"01".contains(args[1])) {
            throw new DecodeException();
        }
        boolean isDone = args[1].equals("1");

        return switch (args[0]) {
            case "T" -> {
                if (args.length != 3) {
                    throw new DecodeException();
                }
                yield new ToDoTask(isDone, args[2]);
            }
            case "D" -> {
                if (args.length != 4) {
                    throw new DecodeException();
                }
                yield new DeadlineTask(isDone, args[2], args[3]);
            }
            case "E" -> {
                if (args.length != 5) {
                    throw new DecodeException();
                }
                yield new EventTask(isDone, args[2], args[3], args[4]);
            }
            default -> throw new DecodeException();
        };
    }

    /**
     * Load the Tasks saved from local into the TaskList
     */
    public void load() {
        File file = new File(this.dataPath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                try {
                    this.list.add(this.decode(data));
                } catch (LauraException e) {
                    UI.send(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            UI.send(e.getMessage());
        }

    }

    /**
     * Add a Task to the TaskList
     * @param task The task to be added
     */
    public void add(Task task) {
        this.list.add(task);
        this.save();
        UI.send("Got it! I've added this task:\n" + task);
    }

    /**
     * Delete a Task from the TaskList
     * @param index The index of the Task in the TaskList to be deleted
     * @throws LauraException If there is no corresponding Task for the index given
     */
    public void delete(int index) throws LauraException {
        if (index > this.list.size() || index < 1) {
            throw new LauraException("Sorry, that task does not exist!");
        }
        Task removed = this.list.remove(index - 1);
        this.save();
        UI.send("Noted. I've removed this task:\n"
                + removed +
                "\nNow you have " + this.list.size() + " in this list.");
    }

    /**
     * Mark a Task from the TaskList
     * @param index The index of the Task in the TaskList to be marked
     * @throws LauraException If there is no corresponding Task for the index given
     */
    public void mark(int index) throws LauraException {
        if (index > this.list.size() || index < 1) {
            throw new LauraException("Sorry, that task does not exist!");
        }
        Task curr = this.list.get(index - 1);
        curr.mark();
        this.save();
        UI.send("Nice! I've marked this task as done:\n" + curr);
    }

    /**
     * Unmark a Task from the TaskList
     * @param index The index of the Task in the TaskList to be unmarked
     * @throws LauraException If there is no corresponding Task for the index given
     */
    public void unmark(int index) throws LauraException {
        if (index > this.list.size() || index < 1) {
            throw new LauraException("Sorry, that task does not exist!");
        }
        Task curr = this.list.get(index - 1);
        curr.unmark();
        this.save();
        UI.send("Ok! I've marked this task as not done:\n" + curr);
    }

    /**
     * Filter the current TaskList to only Tasks which has the keyword
     * @param keyword The keyword to filter by
     */
    public void find(String keyword) {
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task task : this.list) {
            if (task.has(keyword)) {
                filtered.add(task);
            }
        }
        TaskList tasklist = new TaskList(dataPath);
        tasklist.list = filtered;
        UI.send("Here are the matching tasks in your list:\n" + tasklist);
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
