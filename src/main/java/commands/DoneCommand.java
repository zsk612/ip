package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.io.IOException;

/**
 * Set selected task as done in Duke.
 */
public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand
     * @param response user input string
     */
    public DoneCommand(String response) {

        super(response);
    }

    /** Override execute() method.
     * @param tasksList TasksList that stores tasks
     * @param ui Ui that shows text user interface
     * @param warningMessages WarningMessages that show warning messages
     * @param storage Storage that reads and updates .txt file
     */
    @Override
    public void execute(TasksList tasksList, Ui ui, WarningMessages warningMessages, Storage storage) {

        String[] commands = response.trim().split(" ", 2);
        String doneIndex = commands[1].trim();
        int taskNumber = Integer.parseInt(doneIndex) - 1;
        tasksList.tasks.get(taskNumber).setDone();
        try {
            storage.updateFile(tasksList);
        } catch (IOException e) {
            System.out.println(warningMessages.ILLEGAL_IO_WARNING);
        }
        ui.printDoneMessage(taskNumber, tasksList);
    }
}
