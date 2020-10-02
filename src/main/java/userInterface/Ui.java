package src.main.java.userInterface;

import src.main.java.exceptions.NoTaskException;
import src.main.java.exceptions.NoTaskNameException;
import src.main.java.exceptions.NoTaskTimeException;
import src.main.java.tasktypes.Task;
import src.main.java.tasktypes.TasksList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Text user interface of Duke.
 */
public class Ui {

    private final String HORIZONTAL_LINE = "---------------------" + "\n";

    /** Gets commands from user input */
    public String getCommandWords() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void printExit() {

        String BYE_MESSAGE = "Bye. Hope to see you again soon!" + "\n";
        System.out.print(HORIZONTAL_LINE + BYE_MESSAGE + HORIZONTAL_LINE);
    }

    public void printGreet() {

        String GREETING_MESSAGE = "Hello! I'm Duke" + "\n"
                + "What can I do for you?" + "\n"
                + "Tell me your plan!" + "\n";
        System.out.print(HORIZONTAL_LINE + GREETING_MESSAGE + HORIZONTAL_LINE);
    }

    public void printDoneMessage(int taskNumber, TasksList tasksList) {

        String DONE_MESSAGE = "Nice! I've marked this task as done: ";
        System.out.println(DONE_MESSAGE);
        System.out.println("\t" + tasksList.tasks.get(taskNumber));
    }

    public void printUndoneMessage(int taskNumber, TasksList tasksList) {

        String UNDONE_MESSAGE = "Oops! I've marked this task as Undone: ";
        System.out.println(UNDONE_MESSAGE);
        System.out.println("\t" + tasksList.tasks.get(taskNumber));
    }

    public void printClearMessage() {

        String CLEAR_MESSAGE = "I've cleared all the tasks!" + "\n";
        System.out.print(HORIZONTAL_LINE + CLEAR_MESSAGE + HORIZONTAL_LINE);
    }

    /**
     * Displays task list with list messages.
     * @throws NoTaskException if there is no task to be shown
     */
    public void displayFormat(TasksList tasksList) throws NoTaskException {

        if (tasksList.tasks.isEmpty()) {
            throw new NoTaskException();
        }

        String LIST_MESSAGE = "Here are the tasks in your list:";
        System.out.println(HORIZONTAL_LINE + LIST_MESSAGE);
        displayTasks(tasksList);
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Lists out all tasks.
     */
    private void displayTasks(TasksList tasksList) {

        int numOfInfo = 1;

        for (Task storedTask : tasksList.tasks) {
            System.out.println(numOfInfo + ". " + storedTask.toString());
            numOfInfo++;
        }
    }

    /**
     * Displays current task that is processed.
     * @param taskIndex the index of the task
     * @param isAdd boolean variable to show whether the task is added or deleted
     */
    public void displayCurrentTask(Integer taskIndex, TasksList tasksList,
                                   WarningMessages warningMessages, boolean isAdd) {

        if(isAdd) {
            String ADD_MESSAGE_RECEIVED_MESSAGE = "Got it. I've added this task:";
            System.out.println(HORIZONTAL_LINE + ADD_MESSAGE_RECEIVED_MESSAGE);
        } else {
            String DELETE_MESSAGE_RECEIVED_MESSAGE = "Got it. I've removed this task:";
            System.out.println(HORIZONTAL_LINE + DELETE_MESSAGE_RECEIVED_MESSAGE);
        }

        try {
            System.out.println("\t" + tasksList.tasks.get(taskIndex).toString());
            int taskNumber = (isAdd) ? tasksList.tasks.size() : tasksList.tasks.size() - 1;
            System.out.println("Now you have " + taskNumber + " task"
                    + ((taskNumber > 1) ? "s" : ""));
        } catch (IndexOutOfBoundsException e) {
            warningMessages.printNoTaskWarning();
        }
        System.out.print(HORIZONTAL_LINE);
    }

    /**
     * Extracts out task name and time from user input.
     * @param response user input
     * @return a string array containing task name and task time
     * @throws NoTaskTimeException if there is no task time found
     * @throws NoTaskNameException if there is no task name found
     */
    public String[] extractWords(String response)
            throws NoTaskTimeException, NoTaskNameException {

        int endIndexOfTask = (response.contains("/")) ?
                response.indexOf("/") : response.length();
        int beginIndexOfTask = response.indexOf(" ");

        String taskName = response.substring(beginIndexOfTask, endIndexOfTask).trim();

        String taskTime = response.substring(endIndexOfTask).split(" ", 2)[1];

        if (taskTime.isEmpty()) {
            throw new NoTaskTimeException();
        } else if (taskName.isEmpty()) {
            throw new NoTaskNameException();
        }

        return new String[]{taskName, taskTime};
    }

    /**
     * Displays tasks found.
     * @param foundTasks an array list of all matching tasks
     */
    public void displayMatchingTasks(ArrayList<Task> foundTasks) {

        if (foundTasks.isEmpty()) {
            String NO_MATCH_MESSAGE = "There is no matching task." + "\n";
            System.out.print(HORIZONTAL_LINE + NO_MATCH_MESSAGE + HORIZONTAL_LINE);
        } else {
            String FIND_MESSAGE = "Here are the matching tasks in your list:";
            System.out.println(HORIZONTAL_LINE + FIND_MESSAGE);
            int numOfInfo = 1;
            for (Task foundTask: foundTasks) {
                System.out.println(numOfInfo + ". " + foundTask.toString());
                numOfInfo++;
            }
            System.out.print(HORIZONTAL_LINE);
        }
    }

    public void printNextCommandMessage() {

        String NEXT_COMMAND_MESSAGE = "What else?" + "\n";
        System.out.print(HORIZONTAL_LINE + NEXT_COMMAND_MESSAGE + HORIZONTAL_LINE);
    }

    /**
     * Extracts out date and time by looking for date strings in YYYY-MM-DD format.
     * @param dateString date string
     * @return date in MMM dd yyyy if the user inputs date in YYYY-MM-DD format;
     * else returns original string
     * @throws DateTimeParseException if the date string input is not a valid date
     */
    public String extractDate(String dateString) throws DateTimeParseException {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(dateString);

        boolean isValidDate = matcher.find();

        if (isValidDate) {
            String match = matcher.group();
            return LocalDate.parse(match).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return dateString;
    }
}
