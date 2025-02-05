package laura;

import laura.exception.LauraException;

/**
 * The chat bot L.A.U.R.A
 */
public class Laura {
    private final TaskList taskList;

    /**
     * Instantiates the task list and loads info
     */
    public Laura() {
        this.taskList = new TaskList("src/main/data/tasks.txt");
        taskList.load();
    }

    /**
     * Running the chat bot
     */
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

    /**
     * Where the code starts
     * @param args Potential command line arguments for main
     */
    public static void main(String[] args) {
        new Laura().run();
    }
}
