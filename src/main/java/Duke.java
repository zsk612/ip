package src.main.java;

import src.main.java.commands.Parser;
import src.main.java.constants.Constants;
import src.main.java.storage.Storage;
import src.main.java.tasktypes.TasksList;
import src.main.java.userInterface.Ui;
import src.main.java.userInterface.WarningMessages;

import java.io.IOException;
import java.util.NoSuchElementException;

public class Duke {

    public static final TasksList tasksList = new TasksList();
    public static final Ui ui = new Ui();
    public static Storage storage = new Storage();

    public static void main(String[] args) {

        start();
        executeLoop();
        finish();
    }

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

    public static void executeLoop() {
        String response = Ui.getCommandWords();
        while(!response.equals(Constants.BYE_CMD)) {
            Parser.handleCommand(response, false);
            Parser.executeCommand(tasksList, ui);
            Ui.printNextCommandMessage();
            response = Ui.getCommandWords();
        }
    }

    public static void finish() {
        Ui.printExit();
    }
}