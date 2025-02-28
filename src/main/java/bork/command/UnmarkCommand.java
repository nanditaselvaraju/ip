package bork.command;

import bork.exception.BorkException;
import bork.storage.Storage;
import bork.task.Task;
import bork.task.TaskList;
import bork.ui.UserInterface;

/**
 * Represents a command to mark a task as not done.
 * Updates the task status, notifies the user, and saves the change.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an {@code UnmarkCommand} by parsing the provided arguments.
     *
     * @param arguments The command argument containing the index of the task to unmark.
     * @throws BorkException If the argument is not a valid integer.
     */
    public UnmarkCommand(String arguments) throws BorkException {
        assert arguments != null : "Arguments should not be null";
        assert !arguments.isEmpty() : "Arguments should not be empty";
        this.taskIndex = parseTaskIndex(arguments);
    }

    /**
     * Parses the task index from the command arguments.
     *
     * @param arguments The raw user input.
     * @return The zero-based index of the task.
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
            throw new BorkException("Invalid task number.");
        }
    }

    /**
     * Executes the command by marking the specified task as not done.
     * The updated task status is displayed to the user, and the changes are saved to storage.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @return A String indicating that the task has been marked as not done.
     * @throws BorkException If the task index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UserInterface should not be null";
        assert storage != null : "Storage should not be null";
      
        Task task = getValidTask(tasks);
        task.markAsNotDone();
        assert !task.isDone() : "Task should be marked as not done";
      
        persistChanges(tasks, storage);
        return ui.showUnmarkedTask(task);
    }

    /**
     * Retrieves a task from the list, ensuring the index is valid.
     *
     * @param tasks The task list.
     * @return The corresponding task.
     * @throws BorkException If the task index is out of bounds.
     */
    private Task getValidTask(TaskList tasks) throws BorkException {
        if (!tasks.isValidIndex(taskIndex)) {
            throw new BorkException("Invalid task number.");
        }
        return tasks.get(taskIndex);
    }

    /**
     * Saves the updated task list to storage.
     *
     * @param tasks   The task list.
     * @param storage The storage system handling persistence.
     * @throws BorkException If saving fails.
     */
    private void persistChanges(TaskList tasks, Storage storage) throws BorkException {
        try {
            storage.save(tasks);
        } catch (BorkException e) {
            System.err.println("Warning: Failed to save task list after unmarking - " + e.getMessage());
            throw e;
        }
    }
}
