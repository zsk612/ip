package src.main.java.commands;

import src.main.java.exceptions.NoTaskNameException;
import src.main.java.exceptions.NoTaskTimeException;
import src.main.java.exceptions.WrongEventFormatException;
import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.io.IOException;

/**
 * Adds an Event task to Duke.
 */
public class AddEventCommand extends Command {

    public final String EVENT_CMD_SEPARATOR = "/at";


    /** Constructor for AddEventCommand
     * @param response user input string
     * */
    public AddEventCommand(String response) {
        super(response);
    }

    /** Override execute() method.
     * @param tasksList TasksList that stores tasks
     * @param ui Ui that shows text user interface
     * @param warningMessages WarningMessages that show warning messages
     * @param storage Storage that reads and updates .txt file
     * @throws IOException if there is something wrong with inputting data into the .txt file
     * @throws WrongEventFormatException if the format for event command input is wrong
     */
    @Override
    public void execute(TasksList tasksList, Ui ui, WarningMessages warningMessages, Storage storage)
            throws IOException, WrongEventFormatException {
        if (!response.contains(EVENT_CMD_SEPARATOR)) {
            throw new WrongEventFormatException();
        }

        try {
            String [] arrOfTaskAndTime = ui.extractWords(response);
            tasksList.addEventTask(arrOfTaskAndTime);
            ui.displayCurrentTask(tasksList.tasks.size() - 1, tasksList, warningMessages, true);
        } catch (NoTaskNameException e) {
            warningMessages.printSpecifyNameWarning();
        } catch (NoTaskTimeException | IndexOutOfBoundsException e) {
            warningMessages.printSpecifyTimeWarning();
        }
        storage.updateFile(tasksList);
    }
}
