package bork.command;

import bork.exception.BorkException;

public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The raw input string entered by the user.
     * @return The corresponding Command object based on the input.
     * @throws BorkException If the input command is not recognised.
     */
    public static Command parse(String input) throws BorkException {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "reset":
                return new ResetCommand();
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(arguments);
            case "unmark":
                return new UnmarkCommand(arguments);
            case "todo":
                return new AddToDoCommand(arguments);
            case "deadline":
                return new AddDeadlineCommand(arguments);
            case "event":
                return new AddEventCommand(arguments);
            case "delete":
                return new DeleteCommand(arguments);
            default:
                throw new BorkException("Unknown bork.command.");
        }
    }
}
