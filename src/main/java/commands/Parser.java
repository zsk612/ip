package src.main.java;

import src.main.java.constants.Constants;
import src.main.java.exceptions.NoTaskException;
import src.main.java.exceptions.WrongCommandFormatException;

import java.io.IOException;

public class Parser {
    public static void handleCommand(String response, boolean isFromFile) throws IOException, NoTaskException, WrongCommandFormatException {

        String[] commands = response.trim().split(" ", 2);

        Command command = null;
        switch (commands[0].trim()) {
            case Constants.DONE_CMD:
                command = new DoneCommand(response);
                break;
            case Constants.UNDONE_CMD:
                command = new UndoneCommand(response);
                break;
            case Constants.CLEAR_CMD:
                command = new ClearCommand(response);
                break;
            case Constants.TODO_CMD:
                command = new AddTodoCommand(response, isFromFile);
                break;
            case Constants.DEADLINE_CMD:
                command = new AddDeadlineCommand(response, isFromFile);
                break;
            case Constants.EVENT_CMD:
                command = new AddEventCommand(response, isFromFile);
                break;
            case Constants.LIST_CMD:
                command = new ListCommand(response);
                break;
            case Constants.DELETE_CMD:
                command = new DeleteCommand(response);
                break;
            default:
                WarningMessages.printIllegalCommandWarning();
                break;
        }
        command.execute(Duke.tasksList, Duke.ui);
    }

    public static String getCommand(char commandWord) {

        switch (commandWord) {
            case 'T':
                return Constants.TODO_CMD;
            case 'D':
                return Constants.DEADLINE_CMD;
            case 'E':
                return Constants.EVENT_CMD;
            default:
                return "";
        }
    }

    public static String getResponse(String input, char commandWord){

        switch (commandWord) {
            case 'T':
                return input.trim();
            case 'D':
                return input.trim().replace("(by:", "/by")
                        .replace(")", "");
            case 'E':
                return input.trim().replace("(at:", "/at")
                        .replace(")", "");
            default:
                return "";
        }
    }
}
