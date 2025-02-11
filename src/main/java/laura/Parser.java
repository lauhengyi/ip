package laura;


import laura.exception.LauraException;
import laura.task.DeadlineTask;
import laura.task.EventTask;
import laura.task.ToDoTask;

/**
 * Deals with command handling for input
 */
public class Parser {
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
        this.shouldExit = false;
        this.taskList = taskList;
    }

    private String handleByeCommand() {
        this.shouldExit = true;
        return Message.goodbye();
    }
    private String handleListCommand() {
        return taskList.toString();
    }
    private String handleToDoCommand(String description) throws LauraException {
        return taskList.add(new ToDoTask(description));
    }
    private String handleDeadlineCommand(String details) throws LauraException {
        // Making sure by values are valid/exists
        int dlI = details.indexOf(" /by ");
        if (dlI == -1) {
            throw new LauraException("Deadline Task has no deadline!");
        }
        // Extracting values
        String description = details.substring(0, dlI);
        String deadline = details.substring(dlI + 5);
        return taskList.add(new DeadlineTask(description, deadline));
    }
    private String handleEventCommand(String details) throws LauraException {
        // Making sure from and to values are valid/exists
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
        // Extracting from and to values
        String from = timing.substring(0, tI);
        String to = timing.substring(tI + 5);

        return taskList.add(new EventTask(description, from, to));
    }
    private int getIndex(String details) {
        int index;
        try {
            index = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            index = -1;
        }
        return index;
    }
    private String handleRemoveCommand(String details) throws LauraException {
        int index = getIndex(details);
        return taskList.delete(index);
    }
    private String handleMarkCommand(String details) throws LauraException {
        int index = getIndex(details);
        return taskList.mark(index);
    }
    private String handleUnmarkCommand(String details) throws LauraException {
        int index = getIndex(details);
        return taskList.unmark(index);
    }
    private String handleTagCommand(String details) throws LauraException {
        String[] args = details.split("\\s+");
        if (args.length != 2) {
            throw new LauraException("tag command should include an index and a valid tag value (No spaces in tag!)");
        }
        int index = getIndex(args[0]);
        String tag = args[1];
        return taskList.tag(index, tag);
    }
    private String handleFindCommand(String keyword) {
        return taskList.find(keyword);
    }

    /**
     * Execute the next command given by the user
     *
     * @throws LauraException if there is an error in the task given by the user
     */
    public String handleCommand(String input) throws LauraException {
        if (input.equals("bye")) {
            return handleByeCommand();
        } else if (input.equals("list")) {
            return handleListCommand();
        } else if (input.startsWith("todo ")) {
            String description = input.substring(5);
            return handleToDoCommand(description);
        } else if (input.startsWith("deadline ")) {
            String details = input.substring(9);
            return handleDeadlineCommand(details);
        } else if (input.startsWith("event ")) {
            String details = input.substring(6);
            return handleEventCommand(details);
        } else if (input.startsWith("remove ")) {
            String details = input.substring(7);
            return handleRemoveCommand(details);
        } else if (input.startsWith("mark ")) {
            String details = input.substring(5);
            return handleMarkCommand(details);
        } else if (input.startsWith("unmark ")) {
            String details = input.substring(7);
            return handleUnmarkCommand(details);
        } else if (input.startsWith("tag ")) {
            String details = input.substring(4);
            return handleTagCommand(details);
        } else if (input.startsWith("find ")) {
            String keyword = input.substring(5);
            return handleFindCommand(keyword);
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
        return this.shouldExit;
    }
}
