package bork.command;

import bork.task.Task;
import bork.task.TaskList;
import bork.task.Event;
import bork.ui.UserInterface;
import bork.storage.Storage;
import bork.exception.BorkException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    public AddEventCommand(String arguments) throws BorkException {
        if (arguments.isEmpty() || !arguments.contains("/from") || !arguments.contains("/to")) {
            throw new BorkException("Invalid format! Use: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        }
        String[] parts = arguments.split(" /from ", 2);
        this.description = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        try {
            this.start = LocalDateTime.parse(timeParts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.end = LocalDateTime.parse(timeParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new BorkException("Invalid date format! Use: yyyy-MM-dd HHmm");
        }
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        Task task = new Event(description, start, end);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks);
    }
}

