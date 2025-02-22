package bork.command;

import bork.task.TaskList;
import bork.ui.UserInterface;
import bork.storage.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
