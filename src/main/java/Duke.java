package src.main.java;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        ArrayList<Task> storedTasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Tell me your plan!");
        String response = in.nextLine();

        while(!response.equals("bye")){
            if ("list".equals(response)) {
                displayFormat(storedTasks);
            } else if (response.startsWith("done ") &&
                    Character.isDigit(response.charAt(5))) {
                int taskNumber = Integer.parseInt(response.split(" ", 2)[1]);
                System.out.println(taskNumber);
                if (taskNumber <= storedTasks.size() && taskNumber >= 1){
                    storedTasks.get(taskNumber - 1).setDone();
                    displayFormat(storedTasks);
                }
            } else if (response.startsWith("todo ")) {
                String[] arrOfTodoResponses = response.split(" ", 2);
                addTodo(storedTasks, arrOfTodoResponses[1]);
            } else if (response.startsWith("deadline ") && response.contains("/by")){
                String[] arrOfDeadlineResponses = response.split("/", 2);
                String[] arrOfDeadlineTasks = arrOfDeadlineResponses[0].split(" ",2);
                String[] arrOfDeadlineTimes = arrOfDeadlineResponses[1].split(" ",2);
                addDeadline(storedTasks, arrOfDeadlineTasks[1], arrOfDeadlineTimes[1]);
            } else if(response.startsWith("event ") && response.contains("/at")) {
                String[] arrOfEventResponses = response.split("/", 2);
                String[] arrOfEventTasks = arrOfEventResponses[0].split(" ",2);
                String[] arrOfEventTimes = arrOfEventResponses[1].split(" ",2);
                addEvent(storedTasks, arrOfEventTasks[1], arrOfEventTimes[1]);
            } else {
                System.out.println("No such command! Please try again");
            }
            System.out.println("What else?");
            response = in.nextLine();
        }
        exit();
        in.close();
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void displayFormat(ArrayList<Task> storedTasks) {
        System.out.println("---------------------");
        System.out.println("Here are the tasks in your list:");
        displayTasks(storedTasks);
        System.out.println("---------------------");
    }

    public static void displayTasks(ArrayList<Task> storedTasks) {
        int numOfInfo = 1;
        for(Task storedTask : storedTasks) {
            System.out.println(numOfInfo + ". " + storedTask.toString());
            numOfInfo++;
        }
    }

    public static void displayCurrentTask(ArrayList<Task> storedTasks) {
        System.out.println("---------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + storedTasks.get(storedTasks.size() - 1).toString());
        System.out.println("Now you have " + storedTasks.size() + " task"
                + ((storedTasks.size() > 1) ? "s" : ""));

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