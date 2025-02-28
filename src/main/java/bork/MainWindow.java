package bork;

import bork.core.Bork;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window of the Bork chatbot GUI.
 * This class handles user input and displays the conversation between the user and the chatbot.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bork bork;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cute cat user.jpg"));
    private Image borkImage = new Image(this.getClass().getResourceAsStream("/images/silly dog bork.jpeg"));

    /**
     * Initialises the controller.
     * Binds the scroll pane's vertical scroll position to the height of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Bork instance and displays the welcome message.
     */
    @FXML
    public void setBork(Bork b) {
        bork = b;
        String welcomeMessage = bork.getUi().showWelcome();

        dialogContainer.getChildren().add(
                DialogBox.getBorkDialog(welcomeMessage, borkImage)
        );
    }

    /**
     * Handles user input when the user presses Enter or clicks the Send button.
     * Displays the user's input and the chatbot's response in the dialog container.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        System.out.println("User input: " + input);

        String response = bork.getResponse(input);
        System.out.println("Bork response: " + response);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBorkDialog(response, borkImage)
        );
        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            System.exit(0);
        }
    }
}
