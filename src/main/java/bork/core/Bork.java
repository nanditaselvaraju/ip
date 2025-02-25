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

    /**
     * Constructs a new Bork instance.
     * Initialises the user interface, loads tasks from storage, and handles
     * any potential loading errors.
     *
     * @param filePath The file path where task data is stored.
     */

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

    /**
     * Runs the Bork application.
     * Displays a welcome message and continuously reads user commands
     * until an exit command is encountered.
     */
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

    /**
     * the main methods to start the Bork application.
     * Initialises and runs the application with the specified file path.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Bork("data/bork.txt").run();
    }
}