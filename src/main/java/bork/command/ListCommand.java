package bork.command;

import bork.storage.Storage;
import bork.task.TaskList;
import bork.ui.UserInterface;

/**
 * Represents a command to list all tasks in the task list.
 * Displays the curren tasks to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displalying all tasks in the task list.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @return A String of the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
