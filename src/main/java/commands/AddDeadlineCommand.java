package src.main.java;

import src.main.java.constants.Constants;
import src.main.java.exceptions.NoTaskNameException;
import src.main.java.exceptions.NoTaskTimeException;
import src.main.java.exceptions.WrongCommandFormatException;
import src.main.java.tasktypes.TasksList;

import java.io.IOException;

public class AddDeadlineCommand extends Command{

    public AddDeadlineCommand(String response, boolean isFromFile) {
        super(response);
        this.isFromFile = isFromFile;
    }

    public void execute(TasksList tasksList, Ui ui) throws IOException, WrongCommandFormatException {
        if(!response.contains(Constants.DDL_CMD_SEPARATOR)) {
            throw new WrongCommandFormatException();
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
