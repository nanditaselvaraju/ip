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
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an {@code AddEventCommand} by parsing the provided arguments.
     * The arguments must contain a description, a start time, and an end time in the format {@code yyyy-MM-dd HHmm}.
     *
     * @param arguments The command arguments containing the task description, start time, and end time.
     * @throws BorkException If the arguments are missing or the date format is incorrect.
     */
    public AddEventCommand(String arguments) throws BorkException {
        assert arguments != null : "Arguments should not be null";

        if (arguments.isEmpty() || !arguments.contains("/from") || !arguments.contains("/to")) {
            throw new BorkException(
                    "Invalid format! Use: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        }
        String[] parts = arguments.split(" /from ", 2);
        assert parts.length == 2 : "Arguments should be split into description and time part";

        this.description = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        assert timeParts.length == 2 : "Time part should be split into start and end time";

        try {
            this.start = LocalDateTime.parse(timeParts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.end = LocalDateTime.parse(timeParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new BorkException("Invalid date format! Use: yyyy-MM-dd HHmm");
        }
        assert this.start != null : "Start time should not be null";
        assert this.end != null : "End time should not be null";
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
