package bork.command;

import bork.task.Task;
import bork.task.TaskList;
import bork.task.ToDo;
import bork.ui.UserInterface;
import bork.storage.Storage;
import bork.exception.BorkException;

public class AddToDoCommand extends Command {
    private String description;

    /**
     * Constructs an {@code AddToDoCommand} by parsing the provided arguemnts.
     * The argument must contain a description of the ToDo task.
     *
     * @param arguments The command arguments containing the description.
     * @throws BorkException If the description is empty.
     */
    public AddToDoCommand(String arguments) throws BorkException {
        if(arguments.isEmpty()) {
            throw new BorkException("Description of a todo cannot be empty.");
        }
        this.description = arguments;
    }

    /**
     * Executes the command by adding a {@link ToDo} task to the task list.
     * The new task is then displayed to the user and saved to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The user interface to display messages.
     * @param storage The storage system to save or load tasks.
     * @throws BorkException If an error occurs while saving the task.
     */
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        Task task = new ToDo(description);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}
