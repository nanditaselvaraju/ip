package bork.command;

import bork.task.TaskList;
import bork.ui.UserInterface;
import bork.storage.Storage;
import bork.exception.BorkException;

public abstract class Command {

    /**
     * Executes the command, performing actions on the given task list,
     * interacting with the user interface, and potentially modifying storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @throws BorkException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException;
    public boolean isExit() {
        return false;
    }
}
