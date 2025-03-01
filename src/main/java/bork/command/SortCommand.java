package bork.command;

import bork.exception.BorkException;
import bork.storage.Storage;
import bork.task.TaskList;
import bork.ui.UserInterface;

/**
 * Represents a command to sort the tasks in chronological order.
 */
public class SortCommand extends Command {

    /**
     * Executes the sort command, sorting the tasks chronologically.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @return A message indicating the sorted task list or a message if there are no tasks to sort.
     * @throws BorkException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        if (tasks.isEmpty()) {
            return ui.showMessage("No tasks to sort.");
        }
        tasks.sortTasks();
        return ui.showSortedTasks(tasks);
    }
}
