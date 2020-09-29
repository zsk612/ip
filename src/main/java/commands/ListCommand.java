package src.main.java.commands;

import src.main.java.userInterface.Ui;
import src.main.java.exceptions.NoTaskException;
import src.main.java.tasktypes.TasksList;

/**
 * List all tasks in Duke.
 */
public class ListCommand extends Command {

    /** Constructor for ListCommand */
    public ListCommand(String response) {

        super(response);
    }

    /** Override execute() method. */
    @Override
    public void execute(TasksList tasksList) throws NoTaskException {
        Ui.displayFormat();
    }
}
