package src.main.java;

import src.main.java.commands.Parser;
import src.main.java.constants.Constants;
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

    /** The list of tasks stored in Duke.  */
    public static final TasksList tasksList = new TasksList();

    public static final Ui ui = new Ui();

    /** Runs the program until termination.  */
    public static void main(String[] args) {

        start();
        executeLoop();
        finish();
    }

    /** Prints the welcome message, ets up the required objects, and loads up the data from the storage file. */
    public static void start() {
        Ui.printGreet();

        try {
            Storage.initFile();
            Storage.readFileContents();
        } catch (IOException e) {
            System.out.println(WarningMessages.ILLEGAL_IO_WARNING);
        } catch (NoSuchElementException e) {
            System.out.println(WarningMessages.NO_ELEMENT_WARNING);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(WarningMessages.ILLEGAL_DONE_INDEX_WARNING);
        }
    }

    /** Reads the user command and executes it, until the user issues the bye command.  */
    public static void executeLoop() {

        String response = Ui.getCommandWords();

        while(!response.equals(Constants.BYE_CMD)) {
            Parser.handleCommand(response, false);
            Parser.executeCommand(tasksList, ui);
            Ui.printNextCommandMessage();
            response = Ui.getCommandWords();
        }
    }
    /** Prints the Goodbye message and exits. */
    public static void finish() {

        Ui.printExit();
    }
}