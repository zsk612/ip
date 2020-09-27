package src.main.java;

import src.main.java.tasktypes.TasksList;

import java.io.IOException;

public class ClearCommand extends Command{

    public ClearCommand(String response) {
        super(response);
    }

    public void execute(TasksList tasksList, Ui ui) throws IOException {
        tasksList.clearTask();
        Storage.updateFile();
    }
}
