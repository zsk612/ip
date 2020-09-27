package src.main.java.tasktypes;

public class Task {

    protected final String description;
    protected boolean isDone;

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

    //return tick or X symbols
    public String getStatusIcon() {

        return (isDone ? "\u2713" : "\u2718");
    }

    public String toString() {

        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
