import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(String arguments) throws BorkException {
        try {
            this.taskIndex = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new BorkException("Invalid task number.");
        }
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new BorkException("Invalid task number.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        ui.showMarkedTask(task);
        storage.save(tasks);
    }
}


