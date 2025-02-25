package bork.command;

import bork.task.Task;
import bork.task.TaskList;
import bork.ui.UserInterface;
import bork.storage.Storage;
import bork.exception.BorkException;


public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a {@code MarkCommand} by parsing the provided argument as a task index.
     *
     * @param arguments The command argument containing the index of the task to mark as done.
     * @throws BorkException If the argument is not a valid integer.
     */
    public MarkCommand(String arguments) throws BorkException {
        try {
            this.taskIndex = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new BorkException("Invalid bork.task number.");
        }
    }

    /**
     * Executes the command by marking the specified task as done.
     * The updated task status is displayed to the user and the changes are saved to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @throws BorkException If the task index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new BorkException("Invalid bork.task number.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        ui.showMarkedTask(task);
        storage.save(tasks);
    }
}


