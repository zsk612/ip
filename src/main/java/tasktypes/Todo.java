package src.main.java.tasktypes;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides toString().
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
