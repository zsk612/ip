package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.io.IOException;

/**
 * Clear a task from Duke.
 */
public class DeleteCommand extends Command{

    /** Constructs DeleteCommand.
     * @param response user input string
     */
    public DeleteCommand(String response) {

        super(response);
    }

    /** Overrides execute() method.
     * @param tasksList TasksList that stores tasks
     * @param ui Ui that shows text user interface
     * @param warningMessages WarningMessages that show warning messages
     * @param storage Storage that reads and updates .txt file
     * @throws IOException if there is something wrong with inputting data into the .txt file
     */
    @Override
    public void execute(TasksList tasksList, Ui ui, WarningMessages warningMessages,
                        Storage storage) throws IOException {
        String[] commands = response.trim().split(" ", 2);
        String deleteIndex = commands[1].trim();
        int taskNumber = Integer.parseInt(deleteIndex);
        if(taskNumber <= tasksList.tasks.size() && taskNumber > 0){
            ui.displayCurrentTask(taskNumber - 1, tasksList, warningMessages, false);
        }
        tasksList.deleteTask(taskNumber - 1);
        storage.updateFile(tasksList);
    }
}
