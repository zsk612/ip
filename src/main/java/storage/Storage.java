package src.main.java;

import src.main.java.commands.Parser;
import src.main.java.exceptions.NoTaskException;
import src.main.java.tasktypes.Task;
import src.main.java.tasktypes.TasksList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static src.main.java.Duke.tasksList;
import static src.main.java.Duke.ui;

public class Storage {

    public static final String FILE_PATHWAY =
            "data/tasks.txt";

    private static File f = new File(FILE_PATHWAY);

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

    public static void readFileContents()
            throws IOException, NoSuchElementException, NoTaskException, IndexOutOfBoundsException {

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

    public static void appendFile() {

        try {
            appendToFile(String.format("%s\n", TasksList.tasks.get(TasksList.tasks.size() - 1).toString()));
        } catch (IOException e) {
            WarningMessages.printIOWarning(e);
        }
    }

    private static void appendToFile(String textToAppend) throws IOException {

        FileWriter fw = new FileWriter(f, true);
        fw.write(textToAppend);
        fw.close();
    }

    public static void updateFile() throws IOException {

        FileWriter fw = new FileWriter(f);
        for (Task storedTask: TasksList.tasks) {
            fw.write(String.format("%s\n", storedTask.toString()));
        }
        fw.close();
    }
}
