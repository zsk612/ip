package src.main.java.tasktypes;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    /** @param by date and time string */
    protected final String by;

    public Deadline(String description, String by) {

        super(description);
        this.by = by;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
