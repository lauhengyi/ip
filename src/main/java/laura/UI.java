package laura;

/**
 * For displaying messages for the user to see
 */
public class UI {
    /**
     * Sends the Welcome Message for when the application begins
     */
    public static void welcomeMessage() {
        UI.send(
                "Hello I'm Laura\n"
                        + "What can I do for you?"
        );
    }

    /**
     * Sends the Goodbye Message for when the application ends
     */
    public static void goodbyeMessage() {
        UI.send("Bye. Hope to see you again soon!");
    }

    /**
     * Sends a Message for the user to see
     * @param message The Message to be sent
     */
    public static void send(String message) {
        String line = "____________________________________________________________";
        System.out.println("\n" + line);
        System.out.println(message);
        System.out.println(line);
    }
}
