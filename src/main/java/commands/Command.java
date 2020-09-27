package src.main.java.commands;

import src.main.java.tasktypes.TasksList;

public abstract class Command {
    protected boolean isFromFile;
    protected final String response;

    public Command(String response) {
        this.response = response;
    }

    public abstract void execute(TasksList tasksList) throws Exception;
}
