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
        ArrayList<String> storedInfos = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Talk with me!");
        String response = in.nextLine();
        while(!response.equals("bye")){
            if(response.equals("list")) {
                display(storedInfos);
                System.out.println("Say something more!");
                response = in.nextLine();
            } else {
                System.out.println("Information stored: " + response);
                storedInfos.add(response);
                System.out.println("Say something more!");
                response = in.nextLine();
            }
        }
        exit();
        in.close();
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void display(ArrayList<String> storedInfos) {
        int numOfInfo = 1;
        for(String storedInfo : storedInfos) {
            System.out.println(numOfInfo + "." + storedInfo);
            numOfInfo++;
        }
    }


    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
