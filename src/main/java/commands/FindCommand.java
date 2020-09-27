package src.main.java.commands;

import src.main.java.tasktypes.Task;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{

    public FindCommand(String response) {
        super(response);
    }

    @Override
    public void execute(TasksList tasksList) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        String[] commands = response.trim().split(" ", 2);
        for(Task task: TasksList.tasks) {
            if(task.getDescription().contains(commands[1])) {
                foundTasks.add(task);
            }
        }
        Ui.displayMatchingTasks(foundTasks);
    }
}
