package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.io.IOException;

public class UndoneCommand extends Command{

    public UndoneCommand(String response) {

        super(response);
    }

    public void execute(TasksList tasksList) {
        String[] commands = response.trim().split(" ", 2);
        String UndoneIndex = commands[1].trim();
        int taskNumber = Integer.parseInt(UndoneIndex) - 1;
        TasksList.tasks.get(taskNumber).setUndone();
        try {
            Storage.updateFile();
        } catch (IOException e) {
            System.out.println(WarningMessages.ILLEGAL_IO_WARNING);
        }
        Ui.printUndoneMessage(taskNumber);
    }
}
