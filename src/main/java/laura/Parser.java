package laura;

import laura.exception.LauraException;
import laura.task.DeadlineTask;
import laura.task.EventTask;
import laura.task.ToDoTask;

import java.util.Scanner;

public class Parser {
    private final Scanner scanner;
    private boolean exit;
    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.scanner = new Scanner(System.in);
        this.exit = false;
        this.taskList = taskList;
    }

    public void handleCommand() throws LauraException {
        String input = this.scanner.nextLine();
        if (input.equals("bye")) {
            UI.goodbyeMessage();
            this.exit = true;
        } else if (input.equals("list")) {
            UI.send(taskList.toString());
        } else if (input.startsWith("todo ")) {
            String description = input.substring(5);
            taskList.add(new ToDoTask(description));
        } else if (input.startsWith("deadline ")) {
            String details = input.substring(9);
            int dlI = details.indexOf(" /by ");
            if (dlI == -1) {
                throw new LauraException("Deadline Laura.Task has no deadline!");
            }
            String description = details.substring(0, dlI);
            String deadline = details.substring(dlI + 5);
            taskList.add(new DeadlineTask(description, deadline));
        } else if (input.startsWith("event ")) {
            String details = input.substring(6);
            int fI = details.indexOf(" /from ");
            if (fI == -1) {
                throw new LauraException("Event Laura.Task has no From value!");
            }
            String description = details.substring(0, fI);
            String timing = details.substring(fI + 7);
            int tI = timing.indexOf(" /to ");
            if (tI == -1) {
                throw new LauraException("Event Laura.Task has no To value!");
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
        } else {
            throw new LauraException("Oops! I don't recognise this command!");
        }
    }

    public boolean hasEnded() {
        return this.exit || !this.scanner.hasNextLine();
    }
}
