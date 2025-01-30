package laura;

public class UI {
    public static void welcomeMessage() {
        UI.send(
                "Hello I'm Laura\n" +
                        "What can I do for you?"
        );
    }

    public static void goodbyeMessage() {
        UI.send("Bye. Hope to see you again soon!");
    }

    public static void send(String message) {
        String line = "____________________________________________________________";
        System.out.println("\n" + line);
        System.out.println(message);
        System.out.println(line);
    }
}
