package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.userInterface.Ui;
import src.main.java.exceptions.NoTaskException;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.WarningMessages;

/**
 * List all tasks in Duke.
 */
public class ListCommand extends Command {

    /** Constructs ListCommand.
     * @param response user input string
     */
    public ListCommand(String response) {
        super(response);
    }

    /** Overrides execute() method.
     * @param tasksList TasksList that stores tasks
     * @param ui Ui that shows text user interface
     * @param warningMessages WarningMessages that show warning messages, which is not used here
     * @param storage Storage that reads and updates .txt file, which is not used here
     * */
    @Override
    public void execute(TasksList tasksList, Ui ui, WarningMessages warningMessages,
                        Storage storage) throws NoTaskException {
        ui.displayFormat(tasksList);
    }
}
