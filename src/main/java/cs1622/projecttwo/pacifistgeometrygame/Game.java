package cs1622.projecttwo.pacifistgeometrygame;

import javafx.animation.AnimationTimer;
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
    // Instantiate canvas from Canvas parent class to determine size of interface
    private Canvas canvas;

    // Instantiate graphics context from GraphicsContext parent class for game graphics
    private GraphicsContext gc;

    // Nano time
    private long lastTime = System.nanoTime();

    // Screen dimensions
    private static final double screenWidth = 1440;
    private static final double screenHeight = 800;


    // Instantiate player object
    private Player player;

    // Border object
    private Border border;

    // Game loop animation timer
    private AnimationTimer gameLoop;

    /**
     * Start the program, open the game's screen and call the function to display the title screen.
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        // Game Title
        primaryStage.setTitle("Pacifist Geometry War Game");

        // Show starting screen
        showStartScreen(primaryStage);
    }

    /**
     * When player opens the game, show the start screen so player can start or quit game.
     * @param primaryStage
     */
    public void showStartScreen(Stage primaryStage) {
        // Create surface of interface (size)
        canvas = new Canvas(screenWidth, screenHeight); // Screen size
        gc = canvas.getGraphicsContext2D(); // Render screen
        gc.setFill(Color.BLACK); // Fill color of rect (black)
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Fill this size rect (screen)

        // Create buttons
        Button startButton = new Button("Start Game");
        Button quitButton = new Button("Quit Game");

        // Set button actions
        startButton.setOnAction(e -> startGame(primaryStage));
        quitButton.setOnAction(e -> primaryStage.close()); // Closes stage and screen, ending program.

        // Button styles
        // Black background, border-radius, color and width , text color and size of font
        startButton.setStyle("-fx-background-color: black; -fx-border-radius: 5; -fx-border-color: cyan; -fx-border-width: 10; -fx-text-fill: cyan; -fx-font-size: 50");
        quitButton.setStyle("-fx-background-color: black; -fx-border-radius: 5; -fx-border-color: cyan; -fx-text-fill: cyan; -fx-border-width: 10; -fx-font-size: 52");

        // Arrange buttons in a vertical box
        VBox buttonBox = new VBox(10); // 10 pixels spacing for box that contains buttons
        buttonBox.getChildren().addAll(startButton, quitButton); // Add buttons to button box
        buttonBox.setAlignment(Pos.CENTER); // Center buttons in box

        // Title display
        Label titleLabel = new Label("Pacifist");
        titleLabel.setTextFill(Color.CYAN); // Set text color to cyan
        titleLabel.setStyle("-fx-font-size: 100; -fx-padding: 50"); // Font size, padding

        // Arrange title and buttons in a larger vertical box
        VBox titleBox = new VBox(100); // 100 pixels spacing
        titleBox.setAlignment(Pos.TOP_CENTER); // Center the title horizontally
        titleBox.getChildren().addAll(titleLabel, buttonBox); // Add title and button box to the VBox

        // Create a StackPane to center the VBox in the scene
        StackPane root = new StackPane();
        root.getChildren().addAll(canvas, titleBox); // Add canvas and titleBox to root, which contains other boxes

        // Create scene and set it
        Scene startScene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    /**
     * Show second screen, the game screen, by calling the start game function after a button click
     * @param primaryStage
     */
    private void startGame(Stage primaryStage) {
        // Clear start screen and start the game screen
        StackPane gameRoot = new StackPane();
        canvas = new Canvas(screenWidth, screenHeight); // Draw new canvas
        gameRoot.getChildren().add(canvas); // Link canvas to gameRoot Stack Pane
        Scene gameScene = new Scene(gameRoot, screenWidth, screenHeight); // Instantiate a new scene
        primaryStage.setScene(gameScene); // Use gameScene on the primaryStage

        // Fetch graphics context from canvas and assign it to gc
        gc = canvas.getGraphicsContext2D();

        // Initialize game objects
        border = new Border(120, 50);
        player = new Player(screenWidth / 2, screenHeight / 2, 200);

        // Game loop
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) / 1_000_000_000.0; // Convert from nanoseconds to seconds
                lastTime = now;

                updateGame(deltaTime);
                renderGame();
            }
        };

        // Update and render game continuously after startGame() is called
        gameLoop.start();
    }

    /**
     * Updates the game within a specified time and passes that to the player object
     */
    public void updateGame(double deltaTime) {
        player.update(deltaTime);
    }

    // Renders the game
    public void renderGame() {
        // Clear the canvas before rendering a new frame
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.BLACK); // Fill color of rect (black)
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Fill this size rect (screen)

        // Render objects
        border.render(gc);
        player.render(gc);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

