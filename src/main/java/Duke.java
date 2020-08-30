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
                System.out.println("---------------------");
                displayTasks(storedTasks);
                System.out.println("---------------------");
            } else if (response.startsWith("done ") &&
                       Character.isDigit(response.charAt(5))) {
                int taskNumber = (response.charAt(5)) - '1';
                if (taskNumber <= storedTasks.size() && taskNumber >= 1){
                    storedTasks.get(taskNumber).setDone();
                    System.out.println("---------------------");
                    displayTasks(storedTasks);
                    System.out.println("---------------------");
                }
            } else {
                System.out.println("Task stored: " + response);
                addTask(storedTasks, response);
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
    public static void displayTasks(ArrayList<Task> storedTasks) {
        int numOfInfo = 1;
        for(Task storedTask : storedTasks) {
            System.out.println(numOfInfo + ". [" + storedTask.getStatusIcon() + "] " + storedTask.description);
            numOfInfo++;
        }
    }

    public static void addTask(ArrayList<Task> storedTasks, String response) {
        Task t = new Task(response);
        storedTasks.add(t);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
