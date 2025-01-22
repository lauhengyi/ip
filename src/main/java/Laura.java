import java.util.ArrayList;
import java.util.Scanner;

public class Laura{
    public static void main(String[] args) {
        String name = "Laura";

        Message.send(
               "Hello I'm " + name + "\n" +
                       "What can I do for you?"
        );

        ArrayList<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                Message.send("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                StringBuilder message = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    message.append(i + 1).append(". ").append(list.get(i)).append("\n");
                }
                Message.send(message.toString());
            } else {
                list.add(input);
                Message.send("Added: " + input);
            }
        }


    }
}
