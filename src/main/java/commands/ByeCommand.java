package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

/**
 * Exits Duke.
 */
public class ByeCommand extends Command {

    /** Constructs ByeCommand
     * @param response user input string
     * */
    public ByeCommand(String response) {
        super(response);
    }

    /** Overrides execute() method.
     * @param tasksList TasksList that stores tasks, which is not used here
     * @param ui Ui that shows text user interface, which is not used here
     * @param warningMessages WarningMessages that show warning messages, which is not used here
     * @param storage Storage that reads and updates .txt file, which is not used here
     */
    @Override
    public void execute(TasksList tasksList, Ui ui,
                        WarningMessages warningMessages, Storage storage) {
        isExit = true;
    }
}
