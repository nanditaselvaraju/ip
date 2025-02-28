package bork;

import java.io.IOException;

import bork.core.Bork;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bork using FXML.
 */
public class Main extends Application {
    private Bork bork = new Bork("data/bork.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBork(bork);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
