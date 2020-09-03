package src.main.java;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final String GREETING_MESSAGE =
            "Hello! I'm Duke" + "\n"
            + "What can I do for you?" + "\n"
            + "Tell me your plan!";
    public static final String LIST_DISPLAY_MESSAGE = "Here are the tasks in your list:";
    public static final String MESSAGE_RECEIVED_MESSAGE = "Got it. I've added this task:";
    public static final String SPECIFY_TIME_WARNING = "Please specify time!";
    public static final String ILLEGAL_CMD_WARNING = "No such command! Please try again!";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String HORIZONTAL_LINE = "---------------------";
    public static final String DDL_CMD_SEPARATOR = "/by";
    public static final String EVENT_CMD_SEPARATOR = "/at";
    public static final String DONE_CMD = "done";
    public static final String BYE_CMD = "bye";
    public static final String TODO_CMD = "todo";
    public static final String DEADLINE_CMD = "deadline";
    public static final String EVENT_CMD = "event";
    public static final String LIST_CMD = "list";

    public static void main(String[] args) {

        greet();
        ArrayList<Task> storedTasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();

        while(!response.equals(BYE_CMD)){

            String[] commands = response.split(" ");

            switch (commands[0]) {
            case DONE_CMD:
                int taskNumber = parseInteger(commands[1]);
                if (taskNumber <= storedTasks.size() && taskNumber >= 1){
                    storedTasks.get(taskNumber - 1).setDone();
                    displayFormat(storedTasks);
                } else {
                    printIllegalCommandWarning();
                }
                break;
            case TODO_CMD:
                addTodo(storedTasks, commands[1]);
                break;
            case DEADLINE_CMD:
                if(response.contains(DDL_CMD_SEPARATOR)){
                    String [] arrOfTaskAndTime = extractWords(response);
                    addDeadline(storedTasks, arrOfTaskAndTime);
                } else {
                    printSpecifyTimeWarning();
                }
                break;
            case EVENT_CMD:
                if(response.contains(EVENT_CMD_SEPARATOR)){
                    String [] arrOfTaskAndTime = extractWords(response);
                    addEvent(storedTasks, arrOfTaskAndTime);
                } else {
                    printSpecifyTimeWarning();
                }
                break;
            case LIST_CMD:
                displayFormat(storedTasks);
                break;
            default:
                printIllegalCommandWarning();
                break;
            }

            System.out.println("What else?");
            response = in.nextLine();

        }

        exit();
        in.close();
    }

    public static void greet() {

        System.out.println(GREETING_MESSAGE);
    }

    public static void displayFormat(ArrayList<Task> storedTasks) {

        System.out.println(HORIZONTAL_LINE);
        System.out.println(LIST_DISPLAY_MESSAGE);
        displayTasks(storedTasks);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void displayTasks(ArrayList<Task> storedTasks) {

        int numOfInfo = 1;

        for(Task storedTask : storedTasks) {
            System.out.println(numOfInfo + ". " + storedTask.toString());
            numOfInfo++;
        }
    }

    public static void displayCurrentTask(ArrayList<Task> storedTasks) {

        System.out.println(HORIZONTAL_LINE);
        System.out.println(MESSAGE_RECEIVED_MESSAGE);
        System.out.println("\t" + storedTasks.get(storedTasks.size() - 1).toString());
        System.out.println("Now you have " + storedTasks.size() + " task"
                + ((storedTasks.size() > 1) ? "s" : ""));
        System.out.println(HORIZONTAL_LINE);
    }

    public static String[] extractWords(String response){

        String[] arrOfResponses = response.split("/", 2);
        String taskName = arrOfResponses[0].split(" ",2)[1];
        String taskTime = arrOfResponses[1].split(" ",2)[1];
        return new String[]{taskName, taskTime};
    }

    public static void addTodo(ArrayList<Task> storedTasks, String response) {

        Todo t = new Todo(response);
        storedTasks.add(t);
        displayCurrentTask(storedTasks);
    }

    public static void addDeadline(ArrayList<Task> storedTasks,
                                   String[] arrOfTaskAndTime) {

        Deadline t = new Deadline(arrOfTaskAndTime[0], arrOfTaskAndTime[1]);
        storedTasks.add(t);
        displayCurrentTask(storedTasks);
    }

    public static void addEvent(ArrayList<Task> storedTasks,
                                String[] arrOfTaskAndTime) {

        Event t = new Event(arrOfTaskAndTime[0], arrOfTaskAndTime[1]);
        storedTasks.add(t);
        displayCurrentTask(storedTasks);
    }

    public static int parseInteger(String str) {

        try {
            return Integer.parseInt(str);
        } catch(NumberFormatException e){
            return -1;
        }
    }

    public static void printIllegalCommandWarning() {

        System.out.println(ILLEGAL_CMD_WARNING);
    }

    public static void printSpecifyTimeWarning() {

        System.out.println(SPECIFY_TIME_WARNING);
    }

    public static void exit() {

        System.out.println(BYE_MESSAGE);
    }
}