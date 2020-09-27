package src.main.java;

import src.main.java.tasktypes.TasksList;

import java.io.IOException;

public class DeleteCommand extends Command{
    public DeleteCommand(String response) {

        super(response);
    }

    public void execute(TasksList tasksList, Ui ui) throws IOException {
        String[] commands = response.trim().split(" ", 2);
        tasksList.deleteTask(commands);
        Storage.updateFile();
    }
}
