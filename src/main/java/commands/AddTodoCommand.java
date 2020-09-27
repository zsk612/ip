package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;

import java.io.IOException;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String response, boolean isFromFile) {

        super(response);
        this.isFromFile = isFromFile;
    }

    public void execute(TasksList tasksList) throws IOException {
        String[] commands = response.trim().split(" ", 2);
        tasksList.addTodoTask(commands, isFromFile);
        Storage.updateFile();
    }
}
