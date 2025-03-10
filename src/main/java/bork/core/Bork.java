package bork.core;

import bork.command.Command;
import bork.command.Parser;
import bork.exception.BorkException;
import bork.storage.Storage;
import bork.task.TaskList;
import bork.ui.UserInterface;

/**
 * Represents the core logic of the Bork application.
 * Handles initialization, command execution, and user interactions.
 */
public class Bork {
    private Storage storage;
    private TaskList tasks;
    private UserInterface ui;

    /**
     * Constructs a new Bork instance.
     * Initializes the user interface, loads tasks from storage, and handles
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

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            return response;
        } catch (BorkException e) {
            return e.getMessage();
        }
    }

    /**
     * Getter method.
     *
     * @return the UI.
     */
    public UserInterface getUi() {
        return this.ui;
    }

    /**
     * The main methods to start the Bork application.
     * Initializes and runs the application with the specified file path.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Bork("data/bork.txt").run();
    }
}
