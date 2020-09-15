package src.main.java;

import src.main.java.exceptions.*;
import src.main.java.tasktypes.*;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.NoSuchElementException;
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
    public static final String UNDONE_CMD = "undone";
    public static final String CLEAR_CMD = "clear";
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
    public static final String ILLEGAL_IO_WARNING =
            "There is something wrong with your Input/output.";

    public static final String FILE_PATHWAY =
            "data\\tasks.txt";

    public static void main(String[] args) {
        File f = new File(FILE_PATHWAY);
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
        printGreet();
        ArrayList<Task> storedTasks = new ArrayList<>();

        try {
            readFileContents(storedTasks);
        } catch (IOException e) {
            System.out.println("oops");
        } catch (NoSuchElementException e) {
            System.out.println("lol no element");
        }

        Scanner in = new Scanner(System.in);
        String response = in.nextLine();
        while(!response.equals(BYE_CMD)) {
            handleCommand(storedTasks, response, false);
            System.out.println("What else?");
            response = in.nextLine();
        }

        printExit();
        in.close();
    }

    public static void handleCommand(ArrayList<Task> storedTasks,
                                     String response, boolean isFromFile) {

        String[] commands = response.trim().split(" ", 2);

        switch (commands[0].trim()) {
        case DONE_CMD:
            executeDoneCommand(storedTasks, commands);
            break;
        case UNDONE_CMD:
            executeUndoneCommand(storedTasks, commands);
            break;
        case CLEAR_CMD:
            executeClearCommand(storedTasks);
            break;
        case TODO_CMD:
            executeTodoCommand(storedTasks, commands, isFromFile);
            break;
        case DEADLINE_CMD:
            try{
                executeDeadlineCommand(storedTasks, response.trim(), isFromFile);
            } catch (WrongCommandFormatException e) {
                printWrongDeadlineCommandWarning();
            }
            break;
        case EVENT_CMD:
            try{
                executeEventCommand(storedTasks, response.trim(), isFromFile);
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
    }

    public static void printGreet() {

        System.out.println(GREETING_MESSAGE);
    }

    public static void printExit() {

        System.out.print(HORIZONTAL_LINE + BYE_MESSAGE + HORIZONTAL_LINE);
    }

    private static void readFileContents(ArrayList<Task> storedTasks)
            throws IOException, NoSuchElementException {

        Path path = Paths.get(FILE_PATHWAY);
        Scanner s = new Scanner(path);
        s.useDelimiter("\n");

        while (s.hasNext()) {

            String targetLine = s.next();
            if (!targetLine.isEmpty()) {
                System.out.println(targetLine);
                char status = targetLine.charAt(4);
                String command = getCommand(targetLine.charAt(1));
                String response = command + " " + getResponse(targetLine.split(" ", 2)[1],
                        targetLine.charAt(1));
                System.out.println(response);
                handleCommand(storedTasks, response, true);
                if (status == '\u2713') {
                    storedTasks.get(storedTasks.size() - 1).setDone();
                } else if (status == '\u2718') {
                    storedTasks.get(storedTasks.size() - 1).setUndone();
                }
            }
        }
    }

    public static String getCommand(char commandWord) {

        switch (commandWord) {
            case 'T':
                return TODO_CMD;
            case 'D':
                return DEADLINE_CMD;
            case 'E':
                return EVENT_CMD;
            default:
                return "";
        }
    }

    public static String getResponse(String input, char commandWord){

        switch (commandWord) {
            case 'T':
                return input.trim();
            case 'D':
                return input.trim().replace("(by:", "/by")
                        .replace(")", "");
            case 'E':
                return input.trim().replace("(at:", "/at")
                        .replace(")", "");
            default:
                return "";
        }
    }

    public static void updateFile(ArrayList<Task> storedTasks) throws IOException {

        FileWriter fw = new FileWriter(Duke.FILE_PATHWAY);
        for (Task storedTask: storedTasks) {
            fw.write(String.format("%s\n", storedTask.toString()));
        }
        fw.close();
    }

    public static void executeClearCommand(ArrayList<Task> storedTasks) {

        storedTasks.clear();
        executeListCommand(storedTasks);
        try {
            updateFile(storedTasks);
        } catch (IOException e) {
            System.out.println("jdai");
        }
    }

    public static void executeListCommand(ArrayList<Task> storedTasks) {

        try {
            displayFormat(storedTasks);
        } catch (NoTaskException e) {
            printNoTaskWarning();
        }
    }

    public static void executeEventCommand(ArrayList<Task> storedTasks,
                                           String taskResponse, boolean isFromFile)
            throws WrongCommandFormatException {

        if (!taskResponse.contains(EVENT_CMD_SEPARATOR)) {
            throw new WrongCommandFormatException();
        }

        try {
            String [] arrOfTaskAndTime = extractWords(taskResponse);
            addEvent(storedTasks, arrOfTaskAndTime, isFromFile);
        } catch (NoTaskNameException e) {
            printSpecifyNameWarning();
        } catch (NoTaskTimeException | IndexOutOfBoundsException e) {
            printSpecifyTimeWarning();
        }
    }

    public static void executeDeadlineCommand(ArrayList<Task> storedTasks,
                                              String taskResponse, boolean isFromFile)
            throws WrongCommandFormatException {

        if(!taskResponse.contains(DDL_CMD_SEPARATOR)) {
            throw new WrongCommandFormatException();
        }

        try {
            String [] arrOfTaskAndTime = extractWords(taskResponse);
            addDeadline(storedTasks, arrOfTaskAndTime, isFromFile);
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
            try {
                updateFile(storedTasks);
            } catch (IOException e) {
                System.out.println("wrong update");
            }
            executeListCommand(storedTasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            printIllegalDoneIndexWarning();
        }
    }

    public static void executeUndoneCommand(ArrayList<Task> storedTasks, String[] commands) {

        try {
            String doneIndex = commands[1].trim();
            int taskNumber = Integer.parseInt(doneIndex);
            storedTasks.get(taskNumber - 1).setUndone();
            try {
                updateFile(storedTasks);
            } catch (IOException e) {
                System.out.println("wrong update");
            }
            executeListCommand(storedTasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            printIllegalDoneIndexWarning();
        }
    }

    public static void executeTodoCommand(ArrayList<Task> storedTasks,
                                          String[] commands, boolean isFromFile) {

        try{
            String todoTask = commands[1].trim();
            Todo t = new Todo(todoTask);
            storedTasks.add(t);
            if (!isFromFile) {
                displayCurrentTask(storedTasks);
            }
        } catch (IndexOutOfBoundsException e) {
            printSpecifyNameWarning();
        }
    }

    public static void displayFormat(ArrayList<Task> storedTasks)
            throws NoTaskException {

        if (storedTasks.isEmpty()) {
            throw new NoTaskException();
        }

        System.out.println(HORIZONTAL_LINE + LIST_DISPLAY_MESSAGE);
        displayTasks(storedTasks);
        System.out.print(HORIZONTAL_LINE);
    }

    public static void displayTasks(ArrayList<Task> storedTasks) {

        int numOfInfo = 1;

        for (Task storedTask : storedTasks) {
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

        try {
            appendToFile(String.format("%s\n", storedTasks.get(storedTasks.size() - 1).toString()));
        } catch (IOException e) {
            printIOWarning(e);
        }
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

    public static void addDeadline(ArrayList<Task> storedTasks,
                                   String[] arrOfTaskAndTime, boolean isFromFile) {

        Deadline t = new Deadline(arrOfTaskAndTime[0], arrOfTaskAndTime[1]);
        storedTasks.add(t);
        if (!isFromFile) {
            displayCurrentTask(storedTasks);
        }
    }

    public static void addEvent(ArrayList<Task> storedTasks,
                                String[] arrOfTaskAndTime, boolean isFromFile) {

        Event t = new Event(arrOfTaskAndTime[0], arrOfTaskAndTime[1]);
        storedTasks.add(t);
        if (!isFromFile) {
            displayCurrentTask(storedTasks);
        }
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(Duke.FILE_PATHWAY, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
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

    public static void printIOWarning(IOException e) {

        System.out.println(ILLEGAL_IO_WARNING + e.getMessage());
    }
}