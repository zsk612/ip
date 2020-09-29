package src.main.java.storage;

import src.main.java.commands.Parser;
import src.main.java.tasktypes.Task;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.WarningMessages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static src.main.java.Duke.tasksList;
import static src.main.java.Duke.ui;

/**
 * Represents the file used to store Duke data.
 */
public class Storage {

    /** Default file path used. */
    public static final String FILE_PATHWAY =
            "data/tasks.txt";

    private static final File f = new File(FILE_PATHWAY);

    /**
     * initializes the file and create a new file if there is no file.
     */
    public static void initFile() {

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
            WarningMessages.printIOWarning(e);
        }
    }

    /**
     * reads file content.
     * @throws IOException if it cannot read data from the file
     * @throws NoSuchElementException if the element requested does not exist
     * @throws IndexOutOfBoundsException if task index requested exceeds the limit
     */
    public static void readFileContents()
            throws IOException, NoSuchElementException, IndexOutOfBoundsException {

        Path path = Paths.get(FILE_PATHWAY);
        Scanner s = new Scanner(path);
        s.useDelimiter("\n");

        while (s.hasNext()) {

            String targetLine = s.next();
            if (!targetLine.isEmpty()) {
                char status = targetLine.charAt(4);
                String command = Parser.getCommand(targetLine.charAt(1));
                String response = command + " " + Parser.getResponse(targetLine.split(" ", 2)[1],
                        targetLine.charAt(1));
                Parser.handleCommand(response, true);
                Parser.executeCommand(tasksList, ui);
                if (status == '\u2713') {
                    TasksList.tasks.get(TasksList.tasks.size() - 1).setDone();
                } else if (status == '\u2718') {
                    TasksList.tasks.get(TasksList.tasks.size() - 1).setUndone();
                }
            }
        }
    }

    /**
     * processes the text appended to file and catches IOException.
     */
    public static void processAppendText() {

        try {
            appendToFile(String.format("%s\n", TasksList.tasks.get(TasksList.tasks.size() - 1).toString()));
        } catch (IOException e) {
            WarningMessages.printIOWarning(e);
        }
    }

    /**
     * appends text to the file when adding new tasks.
     * @param textToAppend string appended to file
     * @throws IOException if the file cannot by overwritten
     */
    private static void appendToFile(String textToAppend) throws IOException {

        FileWriter fw = new FileWriter(FILE_PATHWAY, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * updates file when changing existing tasks.
     */
    public static void updateFile() throws IOException {

        FileWriter fw = new FileWriter(f);
        for (Task storedTask: TasksList.tasks) {
            fw.write(String.format("%s\n", storedTask.toString()));
        }
        fw.close();
    }
}
