package src.main.java.tasktypes;

/**
 * Represents a Deadline task.
 */
public class Event extends Task {

    /** @param at date and time string */
    protected final String at;

    public Event(String description, String at) {

        super(description);
        this.at = at;
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}

