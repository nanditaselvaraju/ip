package bork.command;

import bork.exception.BorkException;
import bork.storage.Storage;
import bork.task.Task;
import bork.task.TaskList;
import bork.ui.UserInterface;

/**
 * Represents a command to mark a task as done.
 * Updates the task status, notifies the user, and saves the change.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a {@code MarkCommand} by parsing the provided argument as a task index.
     *
     * @param arguments The command argument containing the index of the task to mark as done.
     * @throws BorkException If the argument is not a valid integer.
     */
    public MarkCommand(String arguments) throws BorkException {
        assert arguments != null : "Arguments should not be null";
        this.taskIndex = parseTaskIndex(arguments);
    }

    /**
     * Parses and validates the task index from the user input.
     *
     * @param arguments The input containing the task number.
     * @return The zero-based task index.
     * @throws BorkException If the input is not a valid integer.
     */
    private int parseTaskIndex(String arguments) throws BorkException {
        try {
            int index = Integer.parseInt(arguments) - 1;
            if (index < 0) {
                throw new BorkException("Task number must be a positive integer.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new BorkException("Task number must be a valid integer.");
        }
    }

    /**
     * Executes the command by marking the specified task as done.
     * The updated task status is displayed to the user and the changes are saved to storage.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @return A String indicating that the task has been marked as done.
     * @throws BorkException If the task index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UserInterface should not be null";
        assert storage != null : "Storage should not be null";
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new BorkException("Invalid task number.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        storage.save(tasks);
        return ui.showMarkedTask(task);
    }
}
