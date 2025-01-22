import java.util.Scanner;

public class Laura{
    public static void main(String[] args) {
        String name = "Laura";

        Message.send(
               "Hello I'm " + name + "\n" +
                       "What can I do for you?"
        );

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                Message.send("Bye. Hope to see you again soon!");
                break;
            }

            Message.send(input);
        }


    }
}
