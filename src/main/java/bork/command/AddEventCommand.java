package bork.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bork.exception.BorkException;
import bork.storage.Storage;
import bork.task.Event;
import bork.task.Task;
import bork.task.TaskList;
import bork.ui.UserInterface;

/**
 * Represents a command to add an even task.
 * Parses the user input to extract the description, start time, and end time.
 */
public class AddEventCommand extends Command {
    private static final String DATE_FORMAT = "yyyy-MM-dd HHmm";
    private static final String EVENT_FORMAT_MESSAGE =
            "Invalid format! Use: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>";

    private final String description;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an {@code AddEventCommand} by parsing the provided arguments.
     * The arguments must contain a description, a start time, and an end time in the format {@code yyyy-MM-dd HHmm}.
     *
     * @param arguments The command arguments containing the task description, start time, and end time.
     * @throws BorkException If the arguments are missing or the date format is incorrect.
     */
    public AddEventCommand(String arguments) throws BorkException {
        assert arguments != null : "Arguments should not be null";
        String[] parsedArgs = parseArguments(arguments);
        this.description = parsedArgs[0];
        this.start = parseDateTime(parsedArgs[1]);
        this.end = parseDateTime(parsedArgs[2]);

        if (end.isBefore(start)) {
            throw new BorkException("End time cannot be before the start time.");
        }
    }

    /**
     * Parses the user arguments into description, start time, and end time parts.
     *
     * @param arguments The command input string.
     * @return A string array containing the description, start time, and end time.
     * @throws BorkException If the format is invalid.
     */
    private String[] parseArguments(String arguments) throws BorkException {
        if (arguments == null || arguments.isBlank() || !arguments.contains("/from") || !arguments.contains("/to")) {
            throw new BorkException(EVENT_FORMAT_MESSAGE);
        }

        String[] parts = arguments.split(" /from ", 2);
        if (parts.length < 2 || parts[0].isBlank()) {
            throw new BorkException(EVENT_FORMAT_MESSAGE);
        }

        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2 || timeParts[0].isBlank() || timeParts[1].isBlank()) {
            throw new BorkException(EVENT_FORMAT_MESSAGE);
        }

        return new String[]{parts[0].trim(), timeParts[0].trim(), timeParts[1].trim()};
    }

    /**
     * Parses a date-time string into a {@code LocalDateTime}.
     *
     * @param dateTimeStr The date-time string to parse.
     * @return The parsed {@code LocalDateTime}.
     * @throws BorkException If the date format is incorrect.
     */
    private LocalDateTime parseDateTime(String dateTimeStr) throws BorkException {
        try {
            return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            throw new BorkException("Invalid date format! Use: " + DATE_FORMAT);
        }
    }

    /**
     * Executes the command by adding an {@link Event} task to the task list.
     * The new task is then displayed to the user and saved to storage.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @return A String message indicating that the task is added.
     * @throws BorkException If an error occurs while saving the task.
     */
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        assert tasks != null : "Task list should not be null";
        assert ui != null : "User interface should not be null";
        assert storage != null : "Storage should not be null";

        Task task = new Event(description, start, end);
        tasks.add(task);
        storage.save(tasks);
        return ui.showTaskAdded(task, tasks.size());
    }
}
