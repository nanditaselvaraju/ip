package bork.command;

import bork.task.TaskList;
import bork.ui.UserInterface;
import bork.storage.Storage;
import bork.exception.BorkException;


public class ResetCommand extends Command {
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        tasks.reset();
        storage.save(tasks);
        ui.showResetMessage();
    }

    public boolean isExit() {
        return false;
    }
}
