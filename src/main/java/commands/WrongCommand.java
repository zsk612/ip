package src.main.java.commands;

import src.main.java.exceptions.IllegalCommandException;
import src.main.java.tasktypes.TasksList;

public class WrongCommand extends Command {
    public WrongCommand(String response) {

        super(response);
    }

    public void execute(TasksList tasksList) throws IllegalCommandException {
        throw new IllegalCommandException();
    }
}
