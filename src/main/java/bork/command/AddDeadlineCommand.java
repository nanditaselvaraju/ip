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
    private String description;
    private LocalDateTime deadline;

    /**
     * Constructs an {@code AddDeadlineCommand} by parsing the provided arguments.
     * The arguments must contain a description and a deadline in the format {@code yyyy-MM-dd HHmm}.
     *
     * @param arguments The command arguments containing the task description and deadline.
     * @throws BorkException If the arguments are missing or the date format is incorrect.
     */
    public AddDeadlineCommand(String arguments) throws BorkException {
        if (arguments.isEmpty() || !arguments.contains("/by")) {
            throw new BorkException("Invalid format! Use: deadline <description> /by <yyyy-MM-dd HHmm>");
        }
        String[] parts = arguments.split(" /by ", 2);
        this.description = parts[0];
        try {
            this.deadline = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new BorkException("Invalid date format! Use: yyyy-MM-dd HHmm");
        }
    }

    /**
     * Executes the command by adding a {@link Deadline} task to the task list.
     * The new task is then displayed to the user and saved to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @throws BorkException If an error occurs while saving the task.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        Task task = new Deadline(description, deadline);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}
