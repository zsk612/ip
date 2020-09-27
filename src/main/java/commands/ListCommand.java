package src.main.java.commands;

import src.main.java.userInterface.Ui;
import src.main.java.exceptions.NoTaskException;
import src.main.java.tasktypes.TasksList;


public class ListCommand extends Command {

    public ListCommand(String response) {
        super(response);
    }

    public void execute(TasksList tasksList) throws NoTaskException {
        Ui.displayFormat();
    }
}
