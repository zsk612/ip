package src.main.java;

import src.main.java.tasktypes.TasksList;

import java.io.IOException;

public class UndoneCommand extends Command{

    public UndoneCommand(String response) {
        super(response);
    }

    public void execute(TasksList tasksList, Ui ui) throws IOException {
        String[] commands = response.trim().split(" ", 2);
        String UndoneIndex = commands[1].trim();
        int taskNumber = Integer.parseInt(UndoneIndex);
        tasksList.tasks.get(taskNumber - 1).setUndone();
        try {
            Duke.updateFile();
        } catch (IOException e) {
            System.out.println("wrong update");
        }
//            executeListCommand(storedTasks);
    }
}
