import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime deadline;

    public AddDeadlineCommand(String arguments) throws BorkException {
        if (arguments.isEmpty() || !arguments.contains("/by")) {
            throw new BorkException("Invalid format! Use: deadline <description> /by <yyyy-MM-dd HHmm>");
        }
        String[] parts = arguments.split(" /by ", 2);
        this.description = parts[0];
        try {
            this.deadline = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new BorkException("Invalid date format! Use: yyyy-MM-dd HHmm");
        }
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        Task task = new Deadline(description, deadline);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}
