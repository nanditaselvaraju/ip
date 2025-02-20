import java.util.Scanner;

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

    public static void main(String[] args) throws BorkException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to reset the task list? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();

        Bork bork = new Bork("data/bork.txt");
        if (input.equals("yes")) {
            new ResetCommand().execute(bork.tasks, bork.ui, bork.storage);
        }
        bork.run();
    }
}