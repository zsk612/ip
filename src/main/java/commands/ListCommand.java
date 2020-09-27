package src.main.java;

import src.main.java.exceptions.NoTaskException;
import src.main.java.tasktypes.TasksList;


public class ListCommand extends Command {

    public ListCommand(String response) {
        super(response);
    }

    public void execute(TasksList tasksList, Ui ui) throws NoTaskException {
        Duke.displayFormat();
    }
}
