package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

/**
 * Represents an executable command.
 */
public abstract class Command {

    protected boolean isExit;
    protected final String response;

    /** Constructs abstract class Command
     * @param response user command input
     */
    public Command(String response) {
        this.response = response;
        this.isExit = false;
    }

    /**
     * Executes the command
     * @param tasksList TasksList that stores tasks
     * @param ui Ui that shows text user interface
     * @param warningMessages WarningMessages that show warning messages
     * @param storage Storage that reads and updates .txt file
     * @throws Exception for general exceptions
     */
    public abstract void execute(TasksList tasksList, Ui ui, WarningMessages warningMessages,
                                 Storage storage) throws Exception;
}
