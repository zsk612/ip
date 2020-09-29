package src.main.java.userInterface;

import src.main.java.constants.Constants;

import java.io.IOException;

/**
 * container for all warning messages
 */
public class WarningMessages {
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
    public static final String NO_ELEMENT_WARNING =
            "Sorry, there is no element.";

    public static void printIllegalCommandWarning() {

        System.out.print(Constants.HORIZONTAL_LINE + ILLEGAL_CMD_WARNING + Constants.HORIZONTAL_LINE);
    }

    public static void printIllegalTaskIndexWarning() {

        System.out.print(Constants.HORIZONTAL_LINE + ILLEGAL_DONE_INDEX_WARNING + Constants.HORIZONTAL_LINE);
    }

    public static void printNoTaskWarning() {

        System.out.print(Constants.HORIZONTAL_LINE + NO_TASK_WARNING + Constants.HORIZONTAL_LINE);
    }

    public static void printWrongEventCommandWarning() {

        System.out.print(Constants.HORIZONTAL_LINE + WRONG_EVENT_COMMAND_WARNING + Constants.HORIZONTAL_LINE);
    }

    public static void printWrongDeadlineCommandWarning() {

        System.out.print(Constants.HORIZONTAL_LINE + WRONG_DDL_COMMAND_WARNING + Constants.HORIZONTAL_LINE);
    }

    public static void printSpecifyTimeWarning() {

        System.out.print(Constants.HORIZONTAL_LINE + SPECIFY_TASK_TIME_WARNING + Constants.HORIZONTAL_LINE);
    }

    public static void printSpecifyNameWarning() {

        System.out.print(Constants.HORIZONTAL_LINE + SPECIFY_TASK_NAME_WARNING + Constants.HORIZONTAL_LINE);
    }

    public static void printIOWarning(IOException e) {

        System.out.println(ILLEGAL_IO_WARNING + e.getMessage());
    }
}
