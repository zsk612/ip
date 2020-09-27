package src.main.java;

import src.main.java.tasktypes.TasksList;

import java.io.IOException;

public class DoneCommand extends Command {

    public DoneCommand(String response) {
        super(response);
    }

    public void execute(TasksList tasksList, Ui ui) throws IOException {
        String[] commands = response.trim().split(" ", 2);
        String doneIndex = commands[1].trim();
        int taskNumber = Integer.parseInt(doneIndex);
        tasksList.tasks.get(taskNumber - 1).setDone();
            try {
                Duke.updateFile();
            } catch (IOException e) {
                System.out.println("wrong update");
            }
//            executeListCommand(storedTasks);
    }
}
