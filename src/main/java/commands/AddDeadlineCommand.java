package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;
import src.main.java.constants.Constants;
import src.main.java.exceptions.NoTaskNameException;
import src.main.java.exceptions.NoTaskTimeException;
import src.main.java.exceptions.WrongDeadlineFormatException;
import src.main.java.tasktypes.TasksList;

import java.io.IOException;

/**
 * Adds a Deadline task to Duke.
 */
public class AddDeadlineCommand extends Command{

    /** Constructor for AddDeadlineCommand */
    public AddDeadlineCommand(String response, boolean isFromFile) {
        super(response);
        this.isFromFile = isFromFile;
    }

    /** Override execute() method.
     * @throws IOException if there is something wrong with inputting data into the .txt file
     * @throws WrongDeadlineFormatException if the format for deadline command input is wrong
     */
    @Override
    public void execute(TasksList tasksList) throws IOException, WrongDeadlineFormatException {
        if(!response.contains(Constants.DDL_CMD_SEPARATOR)) {
            throw new WrongDeadlineFormatException();
        }

        try {
            String [] arrOfTaskAndTime = Ui.extractWords(response);
            TasksList.addDeadlineTask(arrOfTaskAndTime, isFromFile);
        } catch (NoTaskNameException e) {
            WarningMessages.printSpecifyNameWarning();
        } catch (NoTaskTimeException | IndexOutOfBoundsException e) {
            WarningMessages.printSpecifyTimeWarning();
        }
        Storage.updateFile();
    }
}
