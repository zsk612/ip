package src.main.java.commands;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;
import src.main.java.constants.Constants;
import src.main.java.exceptions.IllegalCommandException;
import src.main.java.exceptions.NoTaskException;
import src.main.java.exceptions.WrongDeadlineFormatException;
import src.main.java.exceptions.WrongEventFormatException;
import src.main.java.tasktypes.TasksList;

import java.io.IOException;

/**
 * Parses user input.
 */
public class Parser {

    private static Command command;

    /**
     * Parses the user input into command for execution.
     * @param response user input for commands
     * @param isFromFile a boolean parameter to show whether the command is read from the .txt file or user input.
     */
    public static void handleCommand(String response, boolean isFromFile) {

        String[] commands = response.trim().split(" ", 2);

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
            case Constants.FIND_CMD:
                command = new FindCommand(response);
                break;
            case Constants.LIST_CMD:
                command = new ListCommand(response);
                break;
            case Constants.DELETE_CMD:
                command = new DeleteCommand(response);
                break;
            default:
                command = new WrongCommand(response);
                break;
        }
    }

    /**
     * Executes commands and catches exceptions
     * @param tasksList TasksList to store tasks from user input and .txt file
     * @param ui Ui for user interface
     */
    public static void executeCommand(TasksList tasksList, Ui ui) {
        try {
            command.execute(tasksList);
        } catch (WrongDeadlineFormatException e) {
            WarningMessages.printWrongDeadlineCommandWarning();
        } catch (WrongEventFormatException e) {
            WarningMessages.printWrongEventCommandWarning();
        } catch (IOException e) {
            WarningMessages.printIOWarning(e);
        } catch (NoTaskException e) {
            WarningMessages.printNoTaskWarning();
        } catch (IllegalCommandException e) {
            WarningMessages.printIllegalCommandWarning();
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            WarningMessages.printIllegalTaskIndexWarning();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the command keyword from .txt file data.
     * @param commandWord character representing different types of task
     * @return command keywords
     */
    public static String getCommand(char commandWord) {

        switch (commandWord) {
            case 'T':
                return Constants.TODO_CMD;
            case 'D':
                return Constants.DEADLINE_CMD;
            case 'E':
                return Constants.EVENT_CMD;
            default:
                return Constants.WRONG_CMD;
        }
    }

    /**
     * reads tasks from .txt file data.
     * @param input task strings in .txt file
     * @param commandWord character representing different types of task
     * @return task contents
     */
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
