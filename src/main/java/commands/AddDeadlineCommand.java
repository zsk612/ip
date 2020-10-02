package src.main.java.commands;

import src.main.java.exceptions.NoTaskNameException;
import src.main.java.exceptions.NoTaskTimeException;
import src.main.java.exceptions.WrongDeadlineFormatException;
import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.io.IOException;

/**
 * Adds a Deadline task to Duke.
 */
public class AddDeadlineCommand extends Command {

    public final String DDL_CMD_SEPARATOR = "/by";

    /**
     * Constructs AddDeadlineCommand.
     * @param response user input string
     */
    public AddDeadlineCommand(String response) {
        super(response);
    }

    /** Overrides execute() method.
     * @param tasksList TasksList that stores tasks
     * @param ui Ui that shows text user interface
     * @param warningMessages WarningMessages that show warning messages
     * @param storage Storage that reads and updates .txt file
     * @throws IOException
     * if there is something wrong with inputting data into the .txt file
     * @throws WrongDeadlineFormatException
     * if the format for deadline command input is wrong
     */
    @Override
    public void execute(TasksList tasksList, Ui ui, WarningMessages warningMessages,
                        Storage storage) throws IOException, WrongDeadlineFormatException {
        if (!response.contains(DDL_CMD_SEPARATOR)) {
            throw new WrongDeadlineFormatException();
        }

        try {
            String [] arrOfTaskAndTime = ui.extractWords(response);
            tasksList.addDeadlineTask(arrOfTaskAndTime);
            ui.displayCurrentTask(tasksList.tasks.size() - 1,
                    tasksList, warningMessages, true);
        } catch (NoTaskNameException e) {
            warningMessages.printSpecifyNameWarning();
        } catch (NoTaskTimeException | IndexOutOfBoundsException e) {
            warningMessages.printSpecifyTimeWarning();
        }
        storage.updateFile(tasksList);
    }
}
