package src.main.java;

import src.main.java.commands.Parser;
import src.main.java.exceptions.NoTaskNameException;
import src.main.java.exceptions.NoTaskTimeException;
import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private final TasksList tasksList;
    private final WarningMessages warningMessages;

    /**
     * Constructs Duke and initializes ui, storage, tasksList and warningMessages.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        tasksList = new TasksList();
        warningMessages = new WarningMessages();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /** Runs the program until termination.  */
    public void run() {
        start();
        executeLoop();
        finish();
    }

    /** Prints the welcome message, sets up the required objects, and loads up the data from the storage file. */
    public void start() {
        ui.printGreet();

        try {
            storage.initFile();
            storage.readFileContents(tasksList);
        } catch (IOException e) {
            System.out.println(warningMessages.ILLEGAL_IO_WARNING);
        } catch (NoSuchElementException e) {
            System.out.println(warningMessages.NO_ELEMENT_WARNING);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(warningMessages.ILLEGAL_DONE_INDEX_WARNING);
        } catch (NoTaskNameException e) {
            System.out.println(warningMessages.SPECIFY_TASK_NAME_WARNING);
        } catch (NoTaskTimeException e) {
            System.out.println(warningMessages.SPECIFY_TASK_TIME_WARNING);
        }
    }

    /** Reads the user command and executes it, until the user issues the bye command.  */
    public void executeLoop() {

        String response = ui.getCommandWords();
        while (true) {
            parser.handleCommand(response);
            boolean isExit = parser.executeCommand(tasksList, ui, warningMessages, storage);
            if(isExit) {
                break;
            } else {
                ui.printNextCommandMessage();
                response = ui.getCommandWords();
            }
        }
    }
    /** Prints the Goodbye message and exits. */
    public void finish() {

        ui.printExit();
    }
}