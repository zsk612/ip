package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.io.IOException;

/**
 * Clear all tasks in Duke.
 */
public class ClearCommand extends Command{

    /** Constructor for ClearCommand
     * @param response user input string
     */
    public ClearCommand(String response) {

        super(response);
    }

    /** Override execute() method.
     * @param tasksList TasksList that stores tasks
     * @param ui Ui that shows text user interface
     * @param warningMessages WarningMessages that show warning messages, which is not used here
     * @param storage Storage that reads and updates .txt file
     * @throws IOException
     * if there is something wrong with inputting data into the .txt file
     */
    @Override
    public void execute(TasksList tasksList, Ui ui, WarningMessages warningMessages, Storage storage)
            throws IOException {
        tasksList.clearTask();
        storage.updateFile(tasksList);
        ui.printClearMessage();
    }
}
