package bork.command;

import bork.storage.Storage;
import bork.task.TaskList;
import bork.ui.UserInterface;

/**
 * Represents a command to list all tasks in the task list.
 * Displays the current tasks to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @return A String of the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UserInterface should not be null";
        if (tasks.isEmpty()) {
            return ui.showMessage("There are no tasks in your list.");
        }

        return ui.showTaskList(tasks);
    }
}
