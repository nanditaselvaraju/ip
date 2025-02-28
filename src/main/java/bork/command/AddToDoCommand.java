package bork.command;

import bork.exception.BorkException;
import bork.storage.Storage;
import bork.task.Task;
import bork.task.TaskList;
import bork.task.ToDo;
import bork.ui.UserInterface;

/**
 * Represents a command to add a ToDo task.
 * Parses the user input to extract the task description.
 */
public class AddToDoCommand extends Command {
    private String description;

    /**
     * Constructs an {@code AddToDoCommand} by parsing the provided arguments.
     * The argument must contain a description of the ToDo task.
     *
     * @param arguments The command arguments containing the description.
     * @throws BorkException If the description is empty.
     */
    public AddToDoCommand(String arguments) throws BorkException {
        assert arguments != null : "Arguments should not be null";

        if (arguments.isEmpty()) {
            throw new BorkException("Description of a todo cannot be empty.");
        }
        this.description = arguments;
    }

    /**
     * Executes the command by adding a {@link ToDo} task to the task list.
     * The new task is then displayed to the user and saved to storage.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @return A String acknowledging the Task added.
     * @throws BorkException If an error occurs while saving the task.
     */
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        assert tasks != null : "Task list should not be null";
        assert ui != null : "User interface should not be null";
        assert storage != null : "Storage should not be null";

        Task task = new ToDo(description);
        tasks.add(task);
        storage.save(tasks);
        return ui.showTaskAdded(task, tasks.size());
    }
}
