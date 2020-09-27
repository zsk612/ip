package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;

import java.io.IOException;

public class ClearCommand extends Command{

    public ClearCommand(String response) {
        super(response);
    }

    public void execute(TasksList tasksList) throws IOException {
        TasksList.clearTask();
        Storage.updateFile();
        Ui.printClearMessage();
    }
}
