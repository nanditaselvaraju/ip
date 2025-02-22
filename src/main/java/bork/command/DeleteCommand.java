package bork.command;

import bork.task.Task;
import bork.task.TaskList;
import bork.ui.UserInterface;
import bork.storage.Storage;
import bork.exception.BorkException;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(String arguments) throws BorkException {
        try {
            this.taskIndex = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new BorkException("Invalid bork.task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new BorkException("Invalid bork.task number.");
        }
        Task removedTask = tasks.remove(taskIndex);
        ui.showTaskRemoved(removedTask, tasks.size());
        storage.save(tasks);
    }
}
