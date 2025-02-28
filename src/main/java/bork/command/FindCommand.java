package bork.command;

import java.util.List;
import java.util.stream.Collectors;

import bork.exception.BorkException;
import bork.storage.Storage;
import bork.task.Task;
import bork.task.TaskList;
import bork.ui.UserInterface;

/**
 * Represents a command to find tasks that match a given keyword.
 * Searches through the task list and displays matching tasks to the user.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a {@code FindCommand} by parsing the provided search keyword.
     *
     * @param arguments The search keyword entered by the user.
     * @throws BorkException If the keyword is empty.
     */
    public FindCommand(String arguments) throws BorkException {
        if (arguments.isEmpty()) {
            throw new BorkException("Please specify a keyword to search for.");
        }
        this.keyword = arguments.trim().toLowerCase();
        assert !this.keyword.isEmpty() : "Keyword should not be empty after trimming.";
    }

    /**
     * Executes the command by filtering tasks that contain the keyword.
     * Displays the matching tasks to the user.
     *
     * @param tasks   The list of tasks to search through.
     * @param ui      The user interface to display messages.
     * @param storage The storage system (not used in this command).
     * @return A String showing whether there are matching tasks, and what they are.
     */
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "UserInterface should not be null";

        List<Task> matchingTasks = tasks.getAllTasks().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        return ui.showMatchingTasks(matchingTasks);
    }
}
