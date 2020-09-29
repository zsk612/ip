package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;

import java.io.IOException;

/**
 * Clear a task from Duke.
 */
public class DeleteCommand extends Command{

    /** Constructor for DeleteCommand */
    public DeleteCommand(String response) {

        super(response);
    }

    /** Override execute() method.
     * @throws IOException if there is something wrong with inputting data into the .txt file
     */
    @Override
    public void execute(TasksList tasksList) throws IOException {
        String[] commands = response.trim().split(" ", 2);
        TasksList.deleteTask(commands);
        Storage.updateFile();
    }
}
