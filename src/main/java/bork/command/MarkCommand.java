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
    private int taskIndex;

    /**
     * Constructs a {@code MarkCommand} by parsing the provided argument as a task index.
     *
     * @param arguments The command argument containing the index of the task to mark as done.
     * @throws BorkException If the argument is not a valid integer.
     */
    public MarkCommand(String arguments) throws BorkException {
        assert arguments != null : "Arguments should not be null";

        try {
            this.taskIndex = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new BorkException("Invalid task number.");
        }
        assert taskIndex >= -1 : "Task index should not be negative";
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
        assert taskIndex >= 0 && taskIndex < tasks.size() : "Task index out of bounds";

        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new BorkException("Invalid task number.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        storage.save(tasks);
        return ui.showMarkedTask(task);
    }
}
