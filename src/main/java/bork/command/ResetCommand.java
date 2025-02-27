package bork.command;

import bork.exception.BorkException;
import bork.storage.Storage;
import bork.task.TaskList;
import bork.ui.UserInterface;

/**
 * Represents a command to reset the task list.
 * Clears all tasks, saves the empty list, and notifies the user.
 */
public class ResetCommand extends Command {

    /**
     * Executes the reset command by clearing all tasks from the task list,
     * saving the empty task list to storage, and displaying a reset message to the user.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @throws BorkException If an error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        tasks.reset();
        storage.save(tasks);
        ui.showResetMessage();
    }

    /**
     * Indicates that this command does not exit the application.
     *
     * @return {@code false}, indicating that the application should continue running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
