package src.main.java;

import src.main.java.exceptions.NoTaskException;
import src.main.java.exceptions.WrongCommandFormatException;
import src.main.java.tasktypes.TasksList;

import java.io.IOException;

public abstract class Command {
    protected boolean isFromFile;
    protected String response;

    public Command(String response) {
        this.response = response;
    }

    public abstract void execute(TasksList tasksList, Ui ui) throws IOException, NoTaskException, WrongCommandFormatException;
}
