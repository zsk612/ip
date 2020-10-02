package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.io.IOException;

/**
 * Set selected task as undone in Duke.
 */
public class UndoneCommand extends Command {

    /** Constructs UndoneCommand.
     * @param response user input string
     */
    public UndoneCommand(String response) {
        super(response);
    }

    /** Overrides execute() method.
     * @param tasksList TasksList that stores tasks
     * @param ui Ui that shows text user interface
     * @param warningMessages WarningMessages that show warning messages
     * @param storage Storage that reads and updates .txt file
     */
    @Override
    public void execute(TasksList tasksList, Ui ui,
                        WarningMessages warningMessages, Storage storage) {
        String[] commands = response.trim().split(" ", 2);
        String UndoneIndex = commands[1].trim();
        int taskNumber = Integer.parseInt(UndoneIndex) - 1;
        tasksList.tasks.get(taskNumber).setUndone();
        try {
            storage.updateFile(tasksList);
        } catch (IOException e) {
            System.out.println(warningMessages.ILLEGAL_IO_WARNING);
        }
        ui.printUndoneMessage(taskNumber, tasksList);
    }
}
