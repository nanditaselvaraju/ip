package bork.core;

import bork.command.Command;
import bork.command.Parser;
import bork.task.TaskList;
import bork.exception.BorkException;
import bork.storage.Storage;
import bork.ui.UserInterface;

public class Bork {
    private Storage storage;
    private TaskList tasks;
    private UserInterface ui;

    public Bork(String filePath) {
        ui = new UserInterface();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (BorkException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BorkException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Bork("data/bork.txt").run();
    }
}