package src.main.java.commands;

import src.main.java.exceptions.IllegalCommandException;
import src.main.java.exceptions.NoTaskException;
import src.main.java.exceptions.WrongDeadlineFormatException;
import src.main.java.exceptions.WrongEventFormatException;
import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.io.IOException;

/**
 * Parses user input.
 */
public class Parser {

    private Command command;
    public final String DONE_CMD = "done";
    public final String UNDONE_CMD = "undone";
    public final String CLEAR_CMD = "clear";
    public final String BYE_CMD = "bye";
    public final String TODO_CMD = "todo";
    public final String DEADLINE_CMD = "deadline";
    public final String EVENT_CMD = "event";
    public final String FIND_CMD = "find";
    public final String LIST_CMD = "list";
    public final String DELETE_CMD = "delete";

    /**
     * Parses the user input into command for execution.
     * @param response user input for commands
     */
    public void handleCommand(String response) {

        String[] commands = response.trim().split(" ", 2);

        switch (commands[0].trim()) {
            case DONE_CMD:
                command = new DoneCommand(response);
                break;
            case UNDONE_CMD:
                command = new UndoneCommand(response);
                break;
            case CLEAR_CMD:
                command = new ClearCommand(response);
                break;
            case TODO_CMD:
                command = new AddTodoCommand(response);
                break;
            case DEADLINE_CMD:
                command = new AddDeadlineCommand(response);
                break;
            case EVENT_CMD:
                command = new AddEventCommand(response);
                break;
            case FIND_CMD:
                command = new FindCommand(response);
                break;
            case LIST_CMD:
                command = new ListCommand(response);
                break;
            case DELETE_CMD:
                command = new DeleteCommand(response);
                break;
            case BYE_CMD:
                command = new ByeCommand(response);
                break;
            default:
                command = new WrongCommand(response);
                break;
        }
    }

    /**
     * Executes commands and catches exceptions.
     * @param tasksList TasksList that stores tasks
     * @param ui Ui that shows text user interface
     * @param warningMessages WarningMessages that show warning messages
     * @param storage Storage that reads and updates .txt file
     * @return boolean isExit to show whether Duke can be terminated
     */
    public boolean executeCommand(TasksList tasksList, Ui ui,
                                  WarningMessages warningMessages, Storage storage) {
        try {
            command.execute(tasksList, ui, warningMessages, storage);
            if (command.isExit) {
                return true;
            }
        } catch (WrongDeadlineFormatException e) {
            warningMessages.printWrongDeadlineCommandWarning();
        } catch (WrongEventFormatException e) {
            warningMessages.printWrongEventCommandWarning();
        } catch (IOException e) {
            warningMessages.printIOWarning(e);
        } catch (NoTaskException e) {
            warningMessages.printNoTaskWarning();
        } catch (IllegalCommandException e) {
            warningMessages.printIllegalCommandWarning();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            warningMessages.printIllegalTaskIndexWarning();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
