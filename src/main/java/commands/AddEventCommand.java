package src.main.java.commands;

import src.main.java.constants.Constants;
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

    /** Constructor for AddEventCommand */
    public AddEventCommand(String response, boolean isFromFile) {
        super(response);
        this.isFromFile = isFromFile;
    }

    /** Override execute() method.
     * @throws IOException if there is something wrong with inputting data into the .txt file
     * @throws WrongEventFormatException if the format for event command input is wrong
     */
    @Override
    public void execute(TasksList tasksList) throws IOException, WrongEventFormatException {
        if(!response.contains(Constants.EVENT_CMD_SEPARATOR)) {
            throw new WrongEventFormatException();
        }

        try {
            String [] arrOfTaskAndTime = Ui.extractWords(response);
            TasksList.addEventTask(arrOfTaskAndTime, isFromFile);
        } catch (NoTaskNameException e) {
            WarningMessages.printSpecifyNameWarning();
        } catch (NoTaskTimeException | IndexOutOfBoundsException e) {
            WarningMessages.printSpecifyTimeWarning();
        }
        Storage.updateFile();
    }
}
