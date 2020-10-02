package src.main.java.tasktypes;

import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the list of tasks stored in Duke.
 */
public class TasksList {

    private final Ui ui;
    private final WarningMessages warningMessages;
    public final ArrayList<Task> tasks;

    public TasksList() {
        ui = new Ui();
        warningMessages = new WarningMessages();
        tasks = new ArrayList<>();
    }

    public int size(){
        return tasks.size();
    }

    /**
     * Adds a todo task in Duke.
     * @param taskName inputs for task name
     */
    public void addTodoTask(String taskName) {
        try{
            String todoTask = taskName.trim();
            Todo t = new Todo(todoTask);
            tasks.add(t);
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            warningMessages.printSpecifyNameWarning();
        }
    }

    /**
     * Adds an event task in Duke.
     * @param arrOfTaskAndTime task name and time inputs for commands
     */
    public void addEventTask(String[] arrOfTaskAndTime) {

        try {
            String date = ui.extractDate(arrOfTaskAndTime[1]);
            Event event = new Event(arrOfTaskAndTime[0], date);
            tasks.add(event);
        } catch (DateTimeParseException e){
            warningMessages.printInvalidDateWarning();
        }
    }

    /**
     * Adds an event task in Duke.
     * @param arrOfTaskAndTime task name and time inputs for commands
     */
    public void addDeadlineTask(String[] arrOfTaskAndTime) {

        try {
            String date = ui.extractDate(arrOfTaskAndTime[1]);
            Deadline ddl = new Deadline(arrOfTaskAndTime[0], date);
            tasks.add(ddl);
        } catch (DateTimeParseException e){
            System.out.println("you may have entered an invalid time");
        }
    }

    /**
     * Deletes a task from Duke.
     * @param taskNumber deleted task index
     */
    public void deleteTask(int taskNumber) {
        try{
            tasks.remove(taskNumber);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            warningMessages.printIllegalTaskIndexWarning();
        }
    }

    /**
     * Clears all tasks from Duke.
     */
    public void clearTask() {
        tasks.clear();
    }

}
