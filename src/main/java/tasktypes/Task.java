package src.main.java.tasktypes;

/**
 * Represents a task in general.
 */
public class Task {

    protected final String description;
    protected boolean isDone;

    /**
     * Constructs tasks.
     * @param description user input
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Processes task description.
     * @return task description
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
