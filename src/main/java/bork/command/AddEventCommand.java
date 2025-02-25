package bork.command;

import bork.task.Task;
import bork.task.TaskList;
import bork.task.Event;
import bork.ui.UserInterface;
import bork.storage.Storage;
import bork.exception.BorkException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        if (arguments.isEmpty() || !arguments.contains("/from") || !arguments.contains("/to")) {
            throw new BorkException("Invalid format! Use: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        }
        String[] parts = arguments.split(" /from ", 2);
        this.description = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        try {
            this.start = LocalDateTime.parse(timeParts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.end = LocalDateTime.parse(timeParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new BorkException("Invalid date format! Use: yyyy-MM-dd HHmm");
        }
    }

    /**
     * Executes the command by adding an {@link Event} task to the task list.
     * The new task is then displayed to the user and saved to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @throws BorkException If an error occurs while saving the task.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        Task task = new Event(description, start, end);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}

