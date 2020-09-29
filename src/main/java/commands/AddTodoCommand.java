package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;

import java.io.IOException;

/**
 * Adds a Todo task to Duke.
 */
public class AddTodoCommand extends Command {

    /** Constructor for AddTodoCommand */
    public AddTodoCommand(String response, boolean isFromFile) {

        super(response);
        this.isFromFile = isFromFile;
    }

    /** Override execute() method.
     * @throws IOException if there is something wrong with inputting data into the .txt file
     */
    @Override
    public void execute(TasksList tasksList) throws IOException {
        String[] commands = response.trim().split(" ", 2);
        tasksList.addTodoTask(commands, isFromFile);
        Storage.updateFile();
    }
}
