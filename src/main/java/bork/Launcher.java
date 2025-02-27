package bork;

import javafx.application.Application;

/**
 * Launcher class to start the JavaFX application.
 * This is required to avoid issues with the JavaFX module system.
 */
public class Launcher {

    /**
     * The main entry point for the application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
