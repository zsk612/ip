package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;

import java.io.IOException;

/**
 * Set selected task as done in Duke.
 */
public class DoneCommand extends Command {

    /** Constructor for DoneCommand */
    public DoneCommand(String response) {

        super(response);
    }

    /** Override execute() method. */
    @Override
    public void execute(TasksList tasksList) {

        String[] commands = response.trim().split(" ", 2);
        String doneIndex = commands[1].trim();
        int taskNumber = Integer.parseInt(doneIndex) - 1;
        TasksList.tasks.get(taskNumber).setDone();
        try {
            Storage.updateFile();
        } catch (IOException e) {
            System.out.println("wrong update");
        }
        Ui.printDoneMessage(taskNumber);
    }
}
