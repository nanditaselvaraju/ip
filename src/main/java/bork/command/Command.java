package bork.command;

import bork.exception.BorkException;
import bork.storage.Storage;
import bork.task.TaskList;
import bork.ui.UserInterface;

/**
 * Represents an abstract command that can be executed.
 * Commands interact with the task list, user interface, and storage.
 */
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

    /**
     * Determines whether this command causes the application to exit.
     * By default, commands do not exit the application.
     *
     * @return {@code false}, indicating that the application should continue running.
     */
    public boolean isExit() {
        return false;
    }
}
