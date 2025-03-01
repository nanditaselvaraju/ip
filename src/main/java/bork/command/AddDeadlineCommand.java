package bork.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bork.exception.BorkException;
import bork.storage.Storage;
import bork.task.Deadline;
import bork.task.Task;
import bork.task.TaskList;
import bork.ui.UserInterface;

/**
 * Represents a command to add a deadline task.
 * Parses the user input to extract the description and deadline.
 */
public class AddDeadlineCommand extends Command {
    private static final String DATE_FORMAT = "yyyy-MM-dd HHmm";
    private static final String DEADLINE_FORMAT_MESSAGE =
            "Invalid format! Use: deadline <description> /by <yyyy-MM-dd HHmm>";
    private final String description;
    private final LocalDateTime deadline;

    /**
     * Constructs an {@code AddDeadlineCommand} by parsing the provided arguments.
     *
     * @param arguments The command arguments containing the task description and deadline.
     * @throws BorkException If the arguments are missing or the date format is incorrect.
     */
    public AddDeadlineCommand(String arguments) throws BorkException {
        assert arguments != null : "Arguments should not be null";

        String[] parsedArgs = parseArguments(arguments);
        this.description = parsedArgs[0];
        this.deadline = parseDeadline(parsedArgs[1]);
    }

    /**
     * Parses the user arguments into description and deadline parts.
     *
     * @param arguments The command input string.
     * @return A string array containing the description and deadline.
     * @throws BorkException If the format is invalid.
     */
    private String[] parseArguments(String arguments) throws BorkException {
        if (arguments == null || arguments.isBlank() || !arguments.contains("/by")) {
            throw new BorkException(DEADLINE_FORMAT_MESSAGE);
        }
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
            throw new BorkException(DEADLINE_FORMAT_MESSAGE);
        }
        return new String[]{parts[0].trim(), parts[1].trim()};
    }

    /**
     * Parses the deadline string into a {@code LocalDateTime}.
     *
     * @param deadlineStr The deadline string to parse.
     * @return The parsed {@code LocalDateTime}.
     * @throws BorkException If the date format is incorrect.
     */
    private LocalDateTime parseDeadline(String deadlineStr) throws BorkException {
        try {
            return LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            throw new BorkException("Invalid date format! Use: " + DATE_FORMAT);
        }
    }

    /**
     * Executes the command by adding a {@link Deadline} task to the task list.
     * The new task is then displayed to the user and saved to storage.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @return A String message showing that the Task is added.
     * @throws BorkException If an error occurs while saving the task.
     */
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        assert tasks != null : "Task list should not be null";
        assert ui != null : "User interface should not be null";
        assert storage != null : "Storage should not be null";

        Task task = new Deadline(description, deadline);
        tasks.add(task);
        storage.save(tasks);
        return ui.showTaskAdded(task, tasks.size());
    }
}
