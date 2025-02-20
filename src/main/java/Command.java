public abstract class Command {
    public abstract void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException;
    public boolean isExit() {
        return false;
    }
}
