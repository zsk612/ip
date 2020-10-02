package src.main.java.tasktypes;

/**
 * Represents a Deadline task.
 */
public class Event extends Task {

    protected final String at;

    /**
     * Constructs Deadline.
     * @param description user input task name
     * @param at user input task time
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Overrides toString().
     * @return event description
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

