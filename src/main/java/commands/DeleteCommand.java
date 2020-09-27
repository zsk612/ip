package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;

import java.io.IOException;

public class DeleteCommand extends Command{
    public DeleteCommand(String response) {

        super(response);
    }

    public void execute(TasksList tasksList) throws IOException {
        String[] commands = response.trim().split(" ", 2);
        TasksList.deleteTask(commands);
        Storage.updateFile();
    }
}
