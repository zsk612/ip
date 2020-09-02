package src.main.java;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final String HORIZONTAL_LINE = "---------------------";
    public static final String DONE_CMD = "done ";
    public static final String BYE_CMD = "bye";
    public static final String TODO_CMD = "todo ";
    public static final String DEADLINE_CMD = "deadline ";
    public static final String EVENT_CMD = "event ";
    public static final String ILLEGAL_CMD_WARNING = "No such command! Please try again";
    public static final String LIST_CMD = "list";
    public static final String DDL_CMD_SEPARATOR = "/by";
    public static final String EVENT_CMD_SEPARATOR = "/at";

    public static void main(String[] args) {
        greet();
        ArrayList<Task> storedTasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Tell me your plan!");
        String response = in.nextLine();

        while(true){
            if (response.equals(BYE_CMD)) {
                break;
            } else if (response.startsWith(DONE_CMD) &&
                    Character.isDigit(response.charAt(5))) {
                int taskNumber = Integer.parseInt(response.split(" ", 2)[1]);
                if (taskNumber <= storedTasks.size() && taskNumber >= 1){
                    storedTasks.get(taskNumber - 1).setDone();
                    displayFormat(storedTasks);
                }
            } else if (response.startsWith(TODO_CMD)) {
                String[] arrOfTodoResponses = response.split(" ", 2);
                addTodo(storedTasks, arrOfTodoResponses[1]);
            } else if (response.startsWith(DEADLINE_CMD) && response.contains(DDL_CMD_SEPARATOR)){
                String[] arrOfDeadlineResponses = response.split("/", 2);
                String[] arrOfDeadlineTasks = arrOfDeadlineResponses[0].split(" ",2);
                String[] arrOfDeadlineTimes = arrOfDeadlineResponses[1].split(" ",2);
                addDeadline(storedTasks, arrOfDeadlineTasks[1], arrOfDeadlineTimes[1]);
            } else if(response.startsWith(EVENT_CMD) && response.contains(EVENT_CMD_SEPARATOR)) {
                String[] arrOfEventResponses = response.split("/", 2);
                String[] arrOfEventTasks = arrOfEventResponses[0].split(" ",2);
                String[] arrOfEventTimes = arrOfEventResponses[1].split(" ",2);
                addEvent(storedTasks, arrOfEventTasks[1], arrOfEventTimes[1]);
            } else if (LIST_CMD.equals(response)) {
                displayFormat(storedTasks);
            } else {
                System.out.println(ILLEGAL_CMD_WARNING);
            }
            System.out.println("What else?");
            response = in.nextLine();
        }
        exit();
        in.close();
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void displayFormat(ArrayList<Task> storedTasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
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
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + storedTasks.get(storedTasks.size() - 1).toString());
        System.out.println("Now you have " + storedTasks.size() + " task"
                + ((storedTasks.size() > 1) ? "s" : ""));
        System.out.println(HORIZONTAL_LINE);

    }

    public static void addTodo(ArrayList<Task> storedTasks, String response) {
        Todo t = new Todo(response);
        storedTasks.add(t);
        displayCurrentTask(storedTasks);
    }

    public static void addDeadline(ArrayList<Task> storedTasks, String response, String by) {
        Deadline t = new Deadline(response, by);
        storedTasks.add(t);
        displayCurrentTask(storedTasks);
    }

    public static void addEvent(ArrayList<Task> storedTasks, String response, String at) {
        Event t = new Event(response, at);
        storedTasks.add(t);
        displayCurrentTask(storedTasks);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}