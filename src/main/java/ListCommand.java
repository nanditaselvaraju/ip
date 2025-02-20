public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
