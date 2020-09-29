package src.main.java.commands;

import src.main.java.tasktypes.TasksList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected boolean isFromFile;
    protected final String response;

    /**
     * @param response user command input
     */
    public Command(String response) {

        this.response = response;
    }

    /**
     * Executes the command.
     */
    public abstract void execute(TasksList tasksList) throws Exception;
}
