package bork.command;

import bork.task.TaskList;
import bork.ui.UserInterface;
import bork.storage.Storage;
import bork.exception.BorkException;

public abstract class Command {
    public abstract void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException;
    public boolean isExit() {
        return false;
    }
}
