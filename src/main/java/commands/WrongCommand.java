package src.main.java.commands;

import src.main.java.exceptions.IllegalCommandException;
import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

/**
 * Show that the input is wrong command.
 */
public class WrongCommand extends Command {

    /** Constructor for WrongCommand. */
    public WrongCommand(String response) {

        super(response);
    }

    /** Override execute() method.
     * @param tasksList TasksList that stores tasks, which is not used here
     * @param ui Ui that shows text user interface, which is not used here
     * @param warningMessages WarningMessages that show warning messages, which is not used here
     * @param storage Storage that reads and updates .txt file, which is not used here
     * @throws IllegalCommandException when the user inputs a wrong command
     */
    @Override
    public void execute(TasksList tasksList, Ui ui, WarningMessages warningMessages, Storage storage)
            throws IllegalCommandException {
        throw new IllegalCommandException();
    }
}
