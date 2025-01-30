import java.util.Scanner;

public class Laura{

    public static void main(String[] args) {
        UI.welcomeMessage();

        TaskList taskList = new TaskList();
        taskList.load();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                UI.send("Bye. Hope to see you again soon!");
                break;
            }
            try {
                handleCommand(input, taskList);
            } catch (LauraException e) {
                UI.send(e.getMessage());
            }
        }
    }

    private static void handleCommand(String input, TaskList taskList) throws LauraException {
        if (input.equals("list")) {
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
        } else {
            throw new LauraException("Oops! I don't recognise this command!");
        }
    }
}
