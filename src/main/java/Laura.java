import java.util.ArrayList;
import java.util.Scanner;

public class Laura{
    public static void main(String[] args) {
        String name = "Laura";

        Message.send(
               "Hello I'm " + name + "\n" +
                       "What can I do for you?"
        );

        TaskList taskList= new TaskList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                Message.send("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                Message.send(taskList.toString());
            } else if (input.startsWith("mark ")) {
                int index;
                try {
                    index = Integer.parseInt(input.substring(5));
                }
                catch (NumberFormatException e) {
                    index = -1;
                }
                taskList.mark(index);
            } else if (input.startsWith("unmark ")) {
                int index;
                try {
                    index = Integer.parseInt(input.substring(7));
                }
                catch (NumberFormatException e) {
                    index = -1;
                }
                taskList.unmark(index);
            } else {
                taskList.add(input);
            }
        }


    }
}
