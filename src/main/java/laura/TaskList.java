package laura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import laura.exception.DecodeException;
import laura.exception.LauraException;
import laura.task.DeadlineTask;
import laura.task.EventTask;
import laura.task.Task;
import laura.task.ToDoTask;


/**
 * Holds a list of Tasks that can be manipulated with various methods
 */
public class TaskList {
    /**
     * Where the local data should be stored
     */
    private final String dataPath;
    /**
     * The list that will hold the tasks
     */
    private ArrayList<Task> list;

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
    private void save() throws LauraException {
        File file = new File(this.dataPath);
        assert file.isFile();
        assert file.canRead();
        String newLine = System.lineSeparator();
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : this.list) {
                writer.write(task.encode() + newLine);
            }
        } catch (IOException e) {
            throw new LauraException("Error saving file!");
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
        String[] args = data.split("\\|", -1);
        if (args.length < 3 || !"TDE".contains(args[0]) || !"01".contains(args[1])) {
            throw new DecodeException();
        }
        System.out.println(Arrays.toString(args));
        boolean isDone = args[1].equals("1");

        return switch (args[0]) {
        case "T" -> {
            if (args.length != 4) {
                throw new DecodeException();
            }
            yield new ToDoTask(isDone, args[2], args[3]);
        }
        case "D" -> {
            if (args.length != 5) {
                throw new DecodeException();
            }
            yield new DeadlineTask(isDone, args[2], args[3], args[4]);
        }
        case "E" -> {
            if (args.length != 6) {
                throw new DecodeException();
            }
            yield new EventTask(isDone, args[2], args[3], args[4], args[5]);
        }
        default -> throw new DecodeException();
        };
    }

    /**
     * Load the Tasks saved from local into the TaskList
     */
    public void load() throws LauraException {
        File file = new File(this.dataPath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                this.list.add(this.decode(data));
            }
        } catch (FileNotFoundException e) {
            this.save();
        }

    }

    /**
     * Add a Task to the TaskList
     *
     * @param task The task to be added
     */
    public String add(Task task) throws LauraException {
        this.list.add(task);
        this.save();
        return ("Got it! I've added this task:\n" + task);
    }

    /**
     * Delete a Task from the TaskList
     *
     * @param index The index of the Task in the TaskList to be deleted
     * @throws LauraException If there is no corresponding Task for the index given
     */
    public String delete(int index) throws LauraException {
        if (index > this.list.size() || index < 1) {
            throw new LauraException("Sorry, that task does not exist!");
        }
        Task removed = this.list.remove(index - 1);
        this.save();
        return ("Noted. I've removed this task:\n"
                + removed + "\nNow you have " + this.list.size() + " in this list.");
    }

    /**
     * Mark a Task from the TaskList
     *
     * @param index The index of the Task in the TaskList to be marked
     * @throws LauraException If there is no corresponding Task for the index given
     */
    public String mark(int index) throws LauraException {
        if (index > this.list.size() || index < 1) {
            throw new LauraException("Sorry, that task does not exist!");
        }
        Task curr = this.list.get(index - 1);
        curr.mark();
        this.save();
        return ("Nice! I've marked this task as done:\n" + curr);
    }

    /**
     * Unmark a Task from the TaskList
     *
     * @param index The index of the Task in the TaskList to be unmarked
     * @throws LauraException If there is no corresponding Task for the index given
     */
    public String unmark(int index) throws LauraException {
        if (index > this.list.size() || index < 1) {
            throw new LauraException("Sorry, that task does not exist!");
        }
        Task curr = this.list.get(index - 1);
        curr.unmark();
        this.save();
        return ("Ok! I've marked this task as not done:\n" + curr);
    }

    /**
     * Tags the Task with the corresponding index with the provided tag
     * @param index The index of the Task in the TaskList to be unmarked
     * @param tag The value of the tag that the Task will be tagged with
     * @throws LauraException If there is no corresponding Task for the index given
     */
    public String tag(int index, String tag) throws LauraException {
        if (index > this.list.size() || index < 1) {
            throw new LauraException("Sorry, that task does not exist!");
        }
        Task curr = this.list.get(index - 1);
        curr.tag(tag);
        this.save();
        return ("Ok! I've tagged this task!:\n" + curr);
    }

    /**
     * Filter the current TaskList to only Tasks which has the keyword
     *
     * @param keyword The keyword to filter by
     */
    public String find(String keyword) {
        ArrayList<Task> filtered = new ArrayList<>();
        for (Task task : this.list) {
            if (task.has(keyword)) {
                filtered.add(task);
            }
        }
        TaskList tasklist = new TaskList(dataPath);
        tasklist.list = filtered;
        return ("Here are the matching tasks in your list:\n" + tasklist);
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
