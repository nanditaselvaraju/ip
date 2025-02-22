package bork.command;

import bork.task.Task;
import bork.task.TaskList;
import bork.task.ToDo;
import bork.ui.UserInterface;
import bork.storage.Storage;
import bork.exception.BorkException;

public class AddToDoCommand extends Command {
    private String description;

    public AddToDoCommand(String arguments) throws BorkException {
        if(arguments.isEmpty()) {
            throw new BorkException("Description of a todo cannot be empty.");
        }
        this.description = arguments;
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        Task task = new ToDo(description);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}
