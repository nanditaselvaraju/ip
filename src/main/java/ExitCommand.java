public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
