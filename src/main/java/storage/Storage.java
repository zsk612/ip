package src.main.java.storage;

import src.main.java.exceptions.NoTaskNameException;
import src.main.java.exceptions.NoTaskTimeException;
import src.main.java.tasktypes.Task;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Represents the file used to store Duke data.
 */
public class Storage {

    public static final String DEADLINE_PREFIX = "deadline ";
    public static final String EVENT_PREFIX = "event ";
    /** Default file path used. */
    private final String FILE_PATHWAY =
            "data/tasks.txt";

    private final File f = new File(FILE_PATHWAY);

    private final Ui ui;
    private final WarningMessages warningMessages;

    public Storage() {
        warningMessages = new WarningMessages();
        ui = new Ui();
    }

    /**
     * initializes the file and create a new file if there is no file.
     */
    public void initFile() {

        boolean doesFileExist = f.exists();

        try {
            // make new directory and create new file if it does not exist
            if (!doesFileExist) {
                f.getParentFile().mkdirs();
                f.createNewFile();
                System.out.println("file path: " + f.getAbsolutePath());
            } else {
                System.out.println("file existed path: " + f.getAbsolutePath());
            }
        } catch (IOException e) {
            warningMessages.printIOWarning(e);
        }
    }

    /**
     * reads file content.
     * @throws IOException if it cannot read data from the file
     * @throws NoSuchElementException if the element requested does not exist
     * @throws IndexOutOfBoundsException if task index requested exceeds the limit
     * @param tasksList TasksList that stores tasks
     */
    public void readFileContents(TasksList tasksList)
            throws IOException, NoSuchElementException, IndexOutOfBoundsException, NoTaskNameException, NoTaskTimeException {

        Path path = Paths.get(FILE_PATHWAY);
        Scanner s = new Scanner(path);
        s.useDelimiter("\n");

        while (s.hasNext()) {

            String targetLine = s.next();
            System.out.println("line: " + targetLine);
            if (!targetLine.isEmpty()) {
                char status = targetLine.charAt(4);
                String response = getResponse(targetLine.split(" ", 2)[1],
                        targetLine.charAt(1));
                System.out.println("response: " + response);
                switch (targetLine.charAt(1)) {
                    case 'T':
                        tasksList.addTodoTask(response);
                        break;
                    case 'D':
                        tasksList.addDeadlineTask(ui.extractWords(DEADLINE_PREFIX + response));
                        System.out.println("ddl: "+ Arrays.toString(ui.extractWords(response)));
                        break;
                    case 'E':
                        tasksList.addEventTask(ui.extractWords(EVENT_PREFIX + response));
                        break;
                    default:
                        break;
                }
                if (status == '\u2713') {
                    tasksList.tasks.get(tasksList.tasks.size() - 1).setDone();
                } else if (status == '\u2718') {
                    tasksList.tasks.get(tasksList.tasks.size() - 1).setUndone();
                }
            }
        }
    }

    /**
     * reads tasks from .txt file data.
     * @param input task strings in .txt file
     * @param commandWord character representing different types of task
     * @return task contents
     */
    public String getResponse(String input, char commandWord) {

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

//    /**
//     * processes the text appended to file and catches IOException.
//     */
//    public void processAppendText() {
//
//        try {
//            appendToFile(String.format("%s\n", tasksList.tasks.get(tasksList.tasks.size() - 1).toString()));
//        } catch (IOException e) {
//            warningMessages.printIOWarning(e);
//        }
//    }
//
//    /**
//     * appends text to the file when adding new tasks.
//     * @param textToAppend string appended to file
//     * @throws IOException if the file cannot by overwritten
//     */
//    private void appendToFile(String textToAppend) throws IOException {
//
//        FileWriter fw = new FileWriter(FILE_PATHWAY, true);
//        fw.write(textToAppend);
//        fw.close();
//    }

    /**
     * updates file when changing existing tasks.
     * @param tasksList TasksList that stores tasks
     */
    public void updateFile(TasksList tasksList) throws IOException {

        FileWriter fw = new FileWriter(f);
        for (Task storedTask: tasksList.tasks) {
            fw.write(String.format("%s\n", storedTask.toString()));
        }
        fw.close();
    }
}
