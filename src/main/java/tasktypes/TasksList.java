package src.main.java.tasktypes;

import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.util.ArrayList;

public class TasksList {


    public static final ArrayList<Task> tasks = new ArrayList<>();

    public int size(){
        return tasks.size();
    }

    public void addTodoTask(String[] commands, boolean isFromFile) {
        try{
            String todoTask = commands[1].trim();
            Todo t = new Todo(todoTask);
            tasks.add(t);
            if (!isFromFile) {
                Ui.displayCurrentTask(tasks.size() - 1, true);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            WarningMessages.printSpecifyNameWarning();
        }
    }

    public static void addEventTask(String[] arrOfTaskAndTime, boolean isFromFile) {

        Event t = new Event(arrOfTaskAndTime[0], arrOfTaskAndTime[1]);
        tasks.add(t);
        if (!isFromFile) {
            Ui.displayCurrentTask(tasks.size() - 1, true);
        }
    }

    public static void addDeadlineTask(String[] arrOfTaskAndTime, boolean isFromFile) {

        Deadline t = new Deadline(arrOfTaskAndTime[0], arrOfTaskAndTime[1]);
        tasks.add(t);
        if (!isFromFile) {
            Ui.displayCurrentTask(tasks.size() - 1, true);
        }
    }

    public static void deleteTask(String[] commands) {
        try{
            String deleteIndex = commands[1].trim();
            int taskNumber = Integer.parseInt(deleteIndex);
            if(taskNumber <= tasks.size() && taskNumber > 0){
                Ui.displayCurrentTask(taskNumber - 1, false);
            }
            tasks.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            WarningMessages.printIllegalTaskIndexWarning();
        }
    }

    public static void clearTask() {
        tasks.clear();
    }

}
