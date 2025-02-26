package bork.command;

import bork.storage.Storage;
import bork.task.Task;
import bork.task.TaskList;
import bork.ui.UserInterface;
import bork.exception.BorkException;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String arguments) throws BorkException {
        if (arguments.isEmpty()) {
            throw new BorkException("Please specify a keyword to search for.");
        }
        this.keyword = arguments.trim().toLowerCase();
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        List<Task> matchingTasks = tasks.getAllTasks().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        ui.showMatchingTasks(matchingTasks);
    }
}
