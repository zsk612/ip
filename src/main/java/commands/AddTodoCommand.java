package src.main.java;

import src.main.java.tasktypes.TasksList;

import java.io.IOException;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String response, boolean isFromFile) {

        super(response);
        this.isFromFile = isFromFile;
    }

    public void execute(TasksList tasksList, Ui ui) throws IOException {
        String[] commands = response.trim().split(" ", 2);
        tasksList.addTodoTask(commands, isFromFile);
        Storage.updateFile();
    }
}
