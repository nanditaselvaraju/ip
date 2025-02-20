import java.io.File;
import java.util.ArrayList;

public class ResetCommand extends Command {
    @Override
    public void execute(TaskList tasks, UserInterface ui, Storage storage) throws BorkException {
        tasks.reset();
        storage.save(tasks);
        ui.showResetMessage();
    }

    public boolean isExit() {
        return false;
    }
}
