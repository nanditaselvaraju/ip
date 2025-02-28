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
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @return A message showing that the list of tasks has been reset.
     * @throws BorkException If an error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UserInterface should not be null";
        assert storage != null : "Storage should not be null";

        tasks.reset();
        storage.save(tasks);
        return ui.showResetMessage();
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
