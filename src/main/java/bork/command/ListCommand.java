package bork.command;

import bork.task.TaskList;
import bork.ui.UserInterface;
import bork.storage.Storage;

public class ListCommand extends Command {

    /**
     * Executes the list command by dsiaplying all tasks in the task list.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
