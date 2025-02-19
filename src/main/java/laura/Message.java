package laura;

/**
 * For displaying messages for the user to see
 */
public class Message {
    /**
     * Sends the Welcome Message for when the application begins
     */
    public static String welcome() {
        return "Hello I'm L.A.U.R.A\n"
                  + "What can I do for you?";
    }

    /**
     * Sends the Goodbye Message for when the application ends
     */
    public static String goodbye() {
        return "Bye. Hope to see you again soon!";
    }
}
