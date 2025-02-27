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
    private int taskIndex;

    /**
     * Constructs an {@code UnmarkCommand} by parsing the provided arguments.
     *
     * @param arguments The command argument containing the index of the task to unmark.
     * @throws BorkException If the argument is not a valid integer.
     */
    public UnmarkCommand(String arguments) throws BorkException {
        try {
            this.taskIndex = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new BorkException("Invalid bork.task number.");
        }
    }

    /**
     * Executes the command by marking the specified task as not done.
     * The updated task status is displayed to the user, and the changes are saved to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @throws BorkException If the task index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new BorkException("Invalid task number.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        ui.showUnmarkedTask(task);
        storage.save(tasks);
    }
}
