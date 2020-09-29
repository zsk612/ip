package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;

import java.io.IOException;

/**
 * Clear all tasks in Duke.
 */
public class ClearCommand extends Command{

    /** Constructor for ClearCommand */
    public ClearCommand(String response) {

        super(response);
    }

    /** Override execute() method.
     * @throws IOException if there is something wrong with inputting data into the .txt file
     */
    @Override
    public void execute(TasksList tasksList) throws IOException {
        TasksList.clearTask();
        Storage.updateFile();
        Ui.printClearMessage();
    }
}
