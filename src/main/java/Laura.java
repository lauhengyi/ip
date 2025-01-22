import java.util.Scanner;

public class Laura{
    public static void main(String[] args) {
        String name = "Laura";
        String line = "____________________________________________________________\n";

        System.out.println(line);
        System.out.println("Hello I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }

            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }


    }
}
