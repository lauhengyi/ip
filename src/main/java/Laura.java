import java.util.Scanner;

public class Laura {
    private final TaskList taskList;

    public Laura() {
        this.taskList = new TaskList("src/main/data/tasks.txt");
        taskList.load();
    }

    public void run() {
        UI.welcomeMessage();
        Parser parser = new Parser(this.taskList);

        while (!parser.hasEnded()) {
            try {
                parser.handleCommand();
            } catch (LauraException e) {
                UI.send(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Laura().run();
    }
}
