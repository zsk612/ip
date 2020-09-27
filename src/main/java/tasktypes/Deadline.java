package src.main.java.tasktypes;

import src.main.java.tasktypes.Task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {

        super(description);
        this.by = by;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}