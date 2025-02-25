package bork.command;

import bork.task.TaskList;
import bork.ui.UserInterface;
import bork.storage.Storage;

public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a goodbye message.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that this command will exit the application.
     *
     * @return {@code true}, indicating that the application should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
