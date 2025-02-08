package laura;

import laura.exception.LauraException;

/**
 * The chat bot L.A.U.R.A
 */
public class Laura {
    private final Parser parser;

    /**
     * Instantiates the task list and loads info
     */
    public Laura() throws LauraException {
        TaskList taskList = new TaskList("src/main/data/tasks.txt");
        taskList.load();
        this.parser = new Parser(taskList);
    }

    /**
     * Running the chat bot
     */
    public String getResponse(String input) {
        if (!parser.hasEnded()) {
            try {
                return parser.handleCommand(input);
            } catch (LauraException e) {
                return e.getMessage();
            }
        } else {
            return "";
        }
    }
}

