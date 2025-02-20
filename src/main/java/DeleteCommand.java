public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(String arguments) throws BorkException {
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
        Task removedTask = tasks.remove(taskIndex);
        ui.showTaskRemoved(removedTask, tasks.size());
        storage.save(tasks);
    }
}
