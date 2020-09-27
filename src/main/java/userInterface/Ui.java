package src.main.java.userInterface;

import src.main.java.constants.Constants;
import src.main.java.exceptions.NoTaskException;
import src.main.java.exceptions.NoTaskNameException;
import src.main.java.exceptions.NoTaskTimeException;
import src.main.java.storage.Storage;
import src.main.java.tasktypes.Task;
import src.main.java.tasktypes.TasksList;

import java.util.Scanner;

public class Ui {

    public static final String GREETING_MESSAGE =
            "Hello! I'm Duke" + "\n"
            + "What can I do for you?" + "\n"
            + "Tell me your plan!" + "\n";
    public static final String LIST_MESSAGE =
            "Here are the tasks in your list:";
    public static final String ADD_MESSAGE_RECEIVED_MESSAGE =
            "Got it. I've added this task:";
    public static final String DELETE_MESSAGE_RECEIVED_MESSAGE =
            "Got it. I've removed this task:";
    public static final String BYE_MESSAGE =
            "Bye. Hope to see you again soon!" + "\n";
    public static final String CLEAR_MESSAGE = "I've cleared all the tasks!";
    public static final String UNDONE_MESSAGE = "Oops! I've marked this task as Undone: ";
    public static final String DONE_MESSAGE = "Nice! I've marked this task as done: ";

    public static String getCommandWords() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void printExit() {

        System.out.print(Constants.HORIZONTAL_LINE + BYE_MESSAGE + Constants.HORIZONTAL_LINE);
    }

    public static void printGreet() {
        System.out.print(Constants.HORIZONTAL_LINE + GREETING_MESSAGE + Constants.HORIZONTAL_LINE);
    }

    public static void printDoneMessage(int taskNumber) {
        System.out.println(DONE_MESSAGE);
        System.out.println("\t" + TasksList.tasks.get(taskNumber));
    }

    public static void printUndoneMessage(int taskNumber) {
        System.out.println(UNDONE_MESSAGE);
        System.out.println("\t" + TasksList.tasks.get(taskNumber));
    }

    public static void printClearMessage() {
        System.out.println(CLEAR_MESSAGE);
    }

    public static void displayFormat()
            throws NoTaskException {

        if (TasksList.tasks.isEmpty()) {
            throw new NoTaskException();
        }

        System.out.println(Constants.HORIZONTAL_LINE + LIST_MESSAGE);
        displayTasks();
        System.out.print(Constants.HORIZONTAL_LINE);
    }

    public static void displayTasks() {

        int numOfInfo = 1;

        for (Task storedTask : TasksList.tasks) {
            System.out.println(numOfInfo + ". " + storedTask.toString());
            numOfInfo++;
        }
    }

    public static void displayCurrentTask(Integer taskIndex, boolean isAdd) {

        if(isAdd) {
            System.out.println(Constants.HORIZONTAL_LINE + ADD_MESSAGE_RECEIVED_MESSAGE);
        } else {
            System.out.println(Constants.HORIZONTAL_LINE + DELETE_MESSAGE_RECEIVED_MESSAGE);
        }

        try {
            System.out.println("\t" + TasksList.tasks.get(taskIndex).toString());
            int taskNumber = (isAdd) ? TasksList.tasks.size() : TasksList.tasks.size() - 1;
            System.out.println("Now you have " + taskNumber + " task"
                    + ((taskNumber > 1) ? "s" : ""));
        } catch (IndexOutOfBoundsException e) {
            WarningMessages.printNoTaskWarning();
        }
        System.out.print(Constants.HORIZONTAL_LINE);

        Storage.appendFile();
    }

    public static String[] extractWords(String response)
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


}
