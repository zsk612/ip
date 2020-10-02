package src.main.java.commands;

import src.main.java.storage.Storage;
import src.main.java.tasktypes.Task;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.util.ArrayList;

/**
 * Find tasks in Duke.
 */
public class FindCommand extends Command {

    /** Constructs FindCommand.
     * @param response user input string
     */
    public FindCommand(String response) {
        super(response);
    }

    /** Overrides execute() method.
     * @param tasksList TasksList that stores tasks
     * @param ui Ui that shows text user interface
     * @param warningMessages WarningMessages that show warning messages
     * @param storage Storage that reads and updates .txt file
     */
    @Override
    public void execute(TasksList tasksList, Ui ui,
                        WarningMessages warningMessages, Storage storage) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        String[] commands = response.trim().split(" ", 2);
        for (Task task: tasksList.tasks) {
            if (task.getDescription().contains(commands[1])) {
                foundTasks.add(task);
            }
        }
        ui.displayMatchingTasks(foundTasks);
    }
}
