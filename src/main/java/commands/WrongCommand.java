package src.main.java.commands;

import src.main.java.exceptions.IllegalCommandException;
import src.main.java.tasktypes.TasksList;

/**
 * Show that the input is wrong command.
 */
public class WrongCommand extends Command {

    /** Constructor for WrongCommand. */
    public WrongCommand(String response) {

        super(response);
    }

    /** Override execute() method.
     * @throws IllegalCommandException when the user inputs a wrong command
     */
    @Override
    public void execute(TasksList tasksList) throws IllegalCommandException {
        throw new IllegalCommandException();
    }
}
