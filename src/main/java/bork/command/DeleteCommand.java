package bork.command;

import bork.exception.BorkException;
import bork.storage.Storage;
import bork.task.Task;
import bork.task.TaskList;
import bork.ui.UserInterface;

/**
 * Represents a command to delete a task from the task list.
 * Removes the specified task, updates the storage, and notifies the user.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} by parsing the provided argument as a task index.
     *
     * @param arguments The command argument containing the index of the task to delete.
     * @throws BorkException If the argument is not a valid integer.
     */
    public DeleteCommand(String arguments) throws BorkException {
        assert arguments != null : "Arguments should not be null";

        try {
            this.taskIndex = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new BorkException("Invalid bork.task number.");
        }
        assert taskIndex >= -1 : "Task index should be at least -1";
    }

    /**
     * Executes the command by deleting the specified task from the task list.
     * The removed task is displayed to the user and the updated task list is saved.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @return A message showing that the task has been removed.
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
        Task removedTask = tasks.remove(taskIndex);
        storage.save(tasks);
        return ui.showTaskRemoved(removedTask, tasks.size());
    }
}
