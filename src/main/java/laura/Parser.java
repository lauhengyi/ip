package laura;

import java.util.Scanner;

import laura.exception.LauraException;
import laura.task.DeadlineTask;
import laura.task.EventTask;
import laura.task.ToDoTask;

/**
 * Deals with command handling for input
 */
public class Parser {
    /** Scanner object to scan for input */
    private final Scanner scanner;
    /** Whether the user has given the command to exit */
    private boolean shouldExit;
    /** Tasklist to manipulate */
    private final TaskList taskList;

    /**
     * To create a Parser instance
     *
     * @param taskList The Tasklist that will be manipulated with the commands
     */
    public Parser(TaskList taskList) {
        this.scanner = new Scanner(System.in);
        this.shouldExit = false;
        this.taskList = taskList;
    }

    /**
     * Execute the next command given by the user
     *
     * @throws LauraException if there is an error in the task given by the user
     */
    public void handleCommand() throws LauraException {
        String input = this.scanner.nextLine();
        if (input.equals("bye")) {
            UI.goodbyeMessage();
            this.shouldExit = true;
        } else if (input.equals("list")) {
            UI.send(taskList.toString());
        } else if (input.startsWith("todo ")) {
            String description = input.substring(5);
            taskList.add(new ToDoTask(description));
        } else if (input.startsWith("deadline ")) {
            String details = input.substring(9);
            int dlI = details.indexOf(" /by ");
            if (dlI == -1) {
                throw new LauraException("Deadline Task has no deadline!");
            }
            String description = details.substring(0, dlI);
            String deadline = details.substring(dlI + 5);
            taskList.add(new DeadlineTask(description, deadline));
        } else if (input.startsWith("event ")) {
            String details = input.substring(6);
            int fI = details.indexOf(" /from ");
            if (fI == -1) {
                throw new LauraException("Event Task has no From value!");
            }
            String description = details.substring(0, fI);
            String timing = details.substring(fI + 7);
            int tI = timing.indexOf(" /to ");
            if (tI == -1) {
                throw new LauraException("Event Task has no To value!");
            }
            String from = timing.substring(0, tI);
            String to = timing.substring(tI + 5);

            taskList.add(new EventTask(description, from, to));
        } else if (input.startsWith("remove ")) {
            int index;
            try {
                index = Integer.parseInt(input.substring(7));
            } catch (NumberFormatException e) {
                index = -1;
            }
            taskList.delete(index);
        } else if (input.startsWith("mark ")) {
            int index;
            try {
                index = Integer.parseInt(input.substring(5));
            } catch (NumberFormatException e) {
                index = -1;
            }
            taskList.mark(index);
        } else if (input.startsWith("unmark ")) {
            int index;
            try {
                index = Integer.parseInt(input.substring(7));
            } catch (NumberFormatException e) {
                index = -1;
            }
            taskList.unmark(index);
        } else if (input.startsWith("find ")) {
            String keyword = input.substring(5);
            taskList.find(keyword);
        } else {
            throw new LauraException("Oops! I don't recognise this command!");
        }
    }

    /**
     * Returns whether the program should end or not
     *
     * @return Whether the program should end
     */
    public boolean hasEnded() {
        return this.shouldExit || !this.scanner.hasNextLine();
    }
}
