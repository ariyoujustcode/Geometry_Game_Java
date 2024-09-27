package cs1622.projecttwo.pacifistgeometrygame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main Game class to run the game, end the game and update score.
 */
public class Game extends Application {
    // Canvas for size of interface
    private Canvas screen_size;

    // GraphicsContext for graphics of interface
    private GraphicsContext graphics;

    @Override
    public void start(Stage primaryStage) {
        // Game Title
        primaryStage.setTitle("Pacifist Geometry War Game");

        // Create surface of interface (size)
        screen_size = new Canvas(900, 650);
        graphics = screen_size.getGraphicsContext2D();
        graphics.setFill(Color.BLACK);
        graphics.fillRect(0, 0, screen_size.getWidth(), screen_size.getHeight());

        // Create buttons
        Button startButton = new Button("Start Game");
        Button quitButton = new Button("Quit Game");

        // Set button actions
        startButton.setOnAction(e -> startGame());
        quitButton.setOnAction(e -> primaryStage.close());

        // Button styles
        startButton.setStyle("-fx-background-color: black; -fx-border-radius: 5; -fx-border-color: cyan; -fx-text-fill: cyan; -fx-font-size: 29");
        quitButton.setStyle("-fx-background-color: black; -fx-border-radius: 5; -fx-border-color: cyan; -fx-text-fill: cyan; -fx-font-size: 30");

        // Arrange buttons in a vertical box
        VBox buttonBox = new VBox(20); // 20 pixels spacing
        buttonBox.getChildren().addAll(startButton, quitButton);
        buttonBox.setAlignment(Pos.CENTER); // Center buttons

        // Title display
        Label titleLabel = new Label("Pacifist");
        titleLabel.setTextFill(Color.CYAN); // Set text color to cyan
        titleLabel.setStyle("-fx-font-size: 80; -fx-padding: 20");

        // Arrange title and buttons in a vertical box
        VBox titleBox = new VBox(100); // 10 pixels spacing
        titleBox.setAlignment(Pos.TOP_CENTER); // Center the title horizontally
        titleBox.getChildren().addAll(titleLabel, buttonBox); // Add title and buttons to the VBox

        // Create a StackPane to center the VBox in the scene
        StackPane root = new StackPane();
        root.getChildren().addAll(screen_size, titleBox); // Add canvas and titleBox to root

        // Create scene and set it
        Scene primaryScene = new Scene(root, 900, 650);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    private void startGame() {
        // Logic to start the game (you can implement your game start logic here)
        System.out.println("Game Started!"); // Placeholder for game start logic
    }

    public static void main(String[] args) {
        launch(args);
    }
}

