package src.main.java.tasktypes;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    protected final String by;

    /**
     * Constructs Deadline.
     * @param description user input task name
     * @param by user input task time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides toString().
     * @return deadline description
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
