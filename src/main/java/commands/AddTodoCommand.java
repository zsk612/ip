package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.io.IOException;

/**
 * Adds a Todo task to Duke.
 */
public class AddTodoCommand extends Command {

    /** Constructor for AddTodoCommand
     * @param response user input string
     */
    public AddTodoCommand(String response) {

        super(response);
    }

    /** Override execute() method.
     * @param tasksList TasksList that stores tasks
     * @param ui Ui that shows text user interface
     * @param warningMessages WarningMessages that show warning messages
     * @param storage Storage that reads and updates .txt file
     * @throws IOException
     * if there is something wrong with inputting data into the .txt file
     */
    @Override
    public void execute(TasksList tasksList, Ui ui, WarningMessages warningMessages, Storage storage)
            throws IOException {
        String[] commands = response.trim().split(" ", 2);
        tasksList.addTodoTask(commands[1]);
        ui.displayCurrentTask(tasksList.tasks.size() - 1, tasksList, warningMessages, true);
        storage.updateFile(tasksList);
    }
}
