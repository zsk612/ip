package src.main.java.userInterface;

import java.io.IOException;

/**
 * container for all warning messages
 */
public class WarningMessages {
    private final String HORIZONTAL_LINE = "---------------------" + "\n";
    public final String ILLEGAL_DONE_INDEX_WARNING =
            "No task with such index found!" + "\n";
    public final String NO_TASK_WARNING =
            "There is no task yet. Please add your task!" + "\n";
    public final String SPECIFY_TASK_NAME_WARNING =
            "Please specify task!" + "\n";
    public final String SPECIFY_TASK_TIME_WARNING = "" +
            "Please specify time!" + "\n";
    public final String ILLEGAL_CMD_WARNING =
            "No such command! Please try again!" + "\n";
    public final String WRONG_DDL_COMMAND_WARNING =
            "Please add \"/by\" in your command!" + "\n";
    public final String WRONG_EVENT_COMMAND_WARNING =
            "Please add \"/at\" in your command!" + "\n";
    public final String ILLEGAL_IO_WARNING =
            "There is something wrong with your Input/output." + "\n";
    public final String NO_ELEMENT_WARNING =
            "Sorry, there is no element." + "\n";
    public final String INVALID_DATE_WARNING =
            "you may have entered an invalid date!" + "\n";

    public void printIllegalCommandWarning() {

        System.out.print(HORIZONTAL_LINE + ILLEGAL_CMD_WARNING + HORIZONTAL_LINE);
    }

    public void printIllegalTaskIndexWarning() {

        System.out.print(HORIZONTAL_LINE + ILLEGAL_DONE_INDEX_WARNING + HORIZONTAL_LINE);
    }

    public void printNoTaskWarning() {

        System.out.print(HORIZONTAL_LINE + NO_TASK_WARNING + HORIZONTAL_LINE);
    }

    public void printWrongEventCommandWarning() {

        System.out.print(HORIZONTAL_LINE + WRONG_EVENT_COMMAND_WARNING + HORIZONTAL_LINE);
    }

    public void printWrongDeadlineCommandWarning() {

        System.out.print(HORIZONTAL_LINE + WRONG_DDL_COMMAND_WARNING + HORIZONTAL_LINE);
    }

    public void printSpecifyTimeWarning() {

        System.out.print(HORIZONTAL_LINE + SPECIFY_TASK_TIME_WARNING + HORIZONTAL_LINE);
    }

    public void printSpecifyNameWarning() {

        System.out.print(HORIZONTAL_LINE + SPECIFY_TASK_NAME_WARNING + HORIZONTAL_LINE);
    }

    public void printIOWarning(IOException e) {

        System.out.print(HORIZONTAL_LINE
                + ILLEGAL_IO_WARNING + e.getMessage() + HORIZONTAL_LINE);
    }

    public void printInvalidDateWarning() {
        System.out.print(HORIZONTAL_LINE + INVALID_DATE_WARNING + HORIZONTAL_LINE);
    }
}
