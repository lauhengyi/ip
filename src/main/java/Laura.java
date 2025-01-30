import java.util.Scanner;

public class Laura{

    public static void main(String[] args) {
        UI.welcomeMessage();

        TaskList taskList = new TaskList();
        taskList.load();

        Parser parser = new Parser(taskList);

        while (!parser.hasEnded()) {
            try {
                parser.handleCommand();
            } catch (LauraException e) {
                UI.send(e.getMessage());
            }
        }
    }
}
