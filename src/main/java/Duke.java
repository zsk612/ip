package src.main.java;

import src.main.java.exceptions.*;
import src.main.java.tasktypes.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final String GREETING_MESSAGE =
            "Hello! I'm Duke" + "\n"
            + "What can I do for you?" + "\n"
            + "Tell me your plan!";
    public static final String LIST_DISPLAY_MESSAGE =
            "Here are the tasks in your list:";
    public static final String MESSAGE_RECEIVED_MESSAGE =
            "Got it. I've added this task:";
    public static final String BYE_MESSAGE =
            "Bye. Hope to see you again soon!" + "\n";

    public static final String HORIZONTAL_LINE = "---------------------" + "\n";
    public static final String DDL_CMD_SEPARATOR = "/by";
    public static final String EVENT_CMD_SEPARATOR = "/at";
    public static final String DONE_CMD = "done";
    public static final String BYE_CMD = "bye";
    public static final String TODO_CMD = "todo";
    public static final String DEADLINE_CMD = "deadline";
    public static final String EVENT_CMD = "event";
    public static final String LIST_CMD = "list";

    public static final String ILLEGAL_DONE_INDEX_WARNING =
            "No task with such index found!" + "\n";
    public static final String NO_TASK_WARNING =
            "There is no task yet. Please add your task!" + "\n";
    public static final String SPECIFY_TASK_NAME_WARNING =
            "Please specify task!" + "\n";
    public static final String SPECIFY_TASK_TIME_WARNING = "" +
            "Please specify time!" + "\n";
    public static final String ILLEGAL_CMD_WARNING =
            "No such command! Please try again!" + "\n";
    public static final String WRONG_DDL_COMMAND_WARNING =
            "Please add \"/by\" in your command!" + "\n";
    public static final String WRONG_EVENT_COMMAND_WARNING =
            "Please add \"/at\" in your command!" + "\n";

    public static void main(String[] args) {

        printGreet();
        ArrayList<Task> storedTasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();

        while(!response.equals(BYE_CMD)){

            String[] commands = response.trim().split(" ", 2);

            switch (commands[0].trim()) {
            case DONE_CMD:
                executeDoneCommand(storedTasks, commands);
                break;
            case TODO_CMD:
                executeTodoCommand(storedTasks, commands);
                break;
            case DEADLINE_CMD:
                try{
                    executeDeadlineCommand(storedTasks, response.trim());
                } catch (WrongCommandFormatException e) {
                    printWrongDeadlineCommandWarning();
                }
                break;
            case EVENT_CMD:
                try{
                    executeEventCommand(storedTasks, response.trim());
                } catch (WrongCommandFormatException e) {
                    printWrongEventCommandWarning();
                }
                break;
            case LIST_CMD:
                executeListCommand(storedTasks);
                break;
            default:
                printIllegalCommandWarning();
                break;
            }

            System.out.println("What else?");
            response = in.nextLine();

        }

        printExit();
        in.close();
    }

    public static void printGreet() {

        System.out.println(GREETING_MESSAGE);
    }

    public static void printExit() {

        System.out.print(HORIZONTAL_LINE + BYE_MESSAGE + HORIZONTAL_LINE);
    }

    public static void executeListCommand(ArrayList<Task> storedTasks) {

        try {
            displayFormat(storedTasks);
        } catch (NoTaskException e) {
            printNoTaskWarning();
        }
    }

    public static void executeEventCommand(ArrayList<Task> storedTasks, String taskResponse)
            throws WrongCommandFormatException {

        if(!taskResponse.contains(EVENT_CMD_SEPARATOR)) {
            throw new WrongCommandFormatException();
        }

        try {
            String [] arrOfTaskAndTime = extractWords(taskResponse);
            addEvent(storedTasks, arrOfTaskAndTime);
        } catch (NoTaskNameException e) {
            printSpecifyNameWarning();
        } catch (NoTaskTimeException | IndexOutOfBoundsException e) {
            printSpecifyTimeWarning();
        }
    }

    public static void executeDeadlineCommand(ArrayList<Task> storedTasks, String taskResponse)
            throws WrongCommandFormatException {

        if(!taskResponse.contains(DDL_CMD_SEPARATOR)) {
            throw new WrongCommandFormatException();
        }

        try {
            String [] arrOfTaskAndTime = extractWords(taskResponse);
            addDeadline(storedTasks, arrOfTaskAndTime);
        } catch (NoTaskNameException e) {
            printSpecifyNameWarning();
        } catch (NoTaskTimeException | IndexOutOfBoundsException e) {
            printSpecifyTimeWarning();
        }
    }

    public static void executeDoneCommand(ArrayList<Task> storedTasks, String[] commands) {

        try {
            String doneIndex = commands[1].trim();
            int taskNumber = Integer.parseInt(doneIndex);
            storedTasks.get(taskNumber - 1).setDone();
            executeListCommand(storedTasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            printIllegalDoneIndexWarning();
        }
    }

    public static void executeTodoCommand(ArrayList<Task> storedTasks, String[] commands) {

        try{
            String todoTask = commands[1].trim();
            Todo t = new Todo(todoTask);
            storedTasks.add(t);
            displayCurrentTask(storedTasks);
        } catch (IndexOutOfBoundsException e) {
            printSpecifyNameWarning();
        }


    }

    public static void displayFormat(ArrayList<Task> storedTasks)
            throws NoTaskException {

        if(storedTasks.isEmpty()) {
            throw new NoTaskException();
        }

        System.out.println(HORIZONTAL_LINE + LIST_DISPLAY_MESSAGE);
        displayTasks(storedTasks);
        System.out.print(HORIZONTAL_LINE);
    }

    public static void displayTasks(ArrayList<Task> storedTasks) {

        int numOfInfo = 1;

        for(Task storedTask : storedTasks) {
            System.out.println(numOfInfo + ". " + storedTask.toString());
            numOfInfo++;
        }
    }

    public static void displayCurrentTask(ArrayList<Task> storedTasks) {

        System.out.println(HORIZONTAL_LINE + MESSAGE_RECEIVED_MESSAGE);
        try {
            System.out.println("\t" + storedTasks.get(storedTasks.size() - 1).toString());
            System.out.println("Now you have " + storedTasks.size() + " task"
                    + ((storedTasks.size() > 1) ? "s" : ""));
        } catch (IndexOutOfBoundsException e) {
            printNoTaskWarning();
        }
        System.out.print(HORIZONTAL_LINE);
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

    public static void addDeadline(ArrayList<Task> storedTasks, String[] arrOfTaskAndTime) {

        Deadline t = new Deadline(arrOfTaskAndTime[0], arrOfTaskAndTime[1]);
        storedTasks.add(t);
        displayCurrentTask(storedTasks);
    }

    public static void addEvent(ArrayList<Task> storedTasks, String[] arrOfTaskAndTime) {

        Event t = new Event(arrOfTaskAndTime[0], arrOfTaskAndTime[1]);
        storedTasks.add(t);
        displayCurrentTask(storedTasks);
    }

    public static void printIllegalCommandWarning() {

        System.out.print(HORIZONTAL_LINE + ILLEGAL_CMD_WARNING + HORIZONTAL_LINE);
    }

    public static void printIllegalDoneIndexWarning() {

        System.out.print(HORIZONTAL_LINE + ILLEGAL_DONE_INDEX_WARNING + HORIZONTAL_LINE);
    }

    public static void printNoTaskWarning() {

        System.out.print(HORIZONTAL_LINE + NO_TASK_WARNING + HORIZONTAL_LINE);
    }

    public static void printWrongEventCommandWarning() {

        System.out.print(HORIZONTAL_LINE + WRONG_EVENT_COMMAND_WARNING + HORIZONTAL_LINE);
    }

    public static void printWrongDeadlineCommandWarning() {

        System.out.print(HORIZONTAL_LINE + WRONG_DDL_COMMAND_WARNING + HORIZONTAL_LINE);
    }

    public static void printSpecifyTimeWarning() {

        System.out.print(HORIZONTAL_LINE + SPECIFY_TASK_TIME_WARNING + HORIZONTAL_LINE);
    }

    public static void printSpecifyNameWarning() {

        System.out.print(HORIZONTAL_LINE + SPECIFY_TASK_NAME_WARNING + HORIZONTAL_LINE);
    }
}