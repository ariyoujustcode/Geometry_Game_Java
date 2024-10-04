package cs1622.projecttwo.pacifistgeometrygame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * Main Game class to run the game, end the game and update score.
 */
public class Game extends Application {
    // Instantiate objects
    private Canvas canvas; // Canvas object
    private GraphicsContext gc; // Graphics context object
    private Player player; // Player object
    private Border border; // Border object for border of the game

    // List of gates
    private List<Gate> gates;

    // Last time a gate spawned
    private double timeSinceLastGate = 0;
    private static final double gateSpawnInterval = 3; // Three second spawn interval

    // List to hold enemies
    private List<Enemy> enemies = new ArrayList<>();
    private double enemySpawnTime = 0; // Keeps track of when to spawn the next enemy
    private static final double enemySpawnInterval = 1.0; // in seconds

    // Nano time
    private long lastTime = System.nanoTime();

    // Game Screen dimensions
    private static final double screenWidth = 1440;
    private static final double screenHeight = 800;

    // private static final double EXPLOSION_RADIUS = 100;

    // Cursor position
    private double mouseX;
    private double mouseY;

    // Game loop animation timer
    private AnimationTimer gameLoop;

    // Random
    private Random random = new Random();

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

    private void handleMouseMovement(MouseEvent event) {
        mouseX = event.getX();
        mouseY = event.getY();
        player.setPosition(mouseX, mouseY, border); // Update player position with mouse coordinates
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
        gameScene.setOnMouseMoved(this::handleMouseMovement);
        primaryStage.setScene(gameScene); // Use gameScene on the primaryStage

        // Fetch graphics context from canvas and assign it to gc
        gc = canvas.getGraphicsContext2D();

        // Initialize game objects
        border = new Border(120, 50);
        player = new Player(screenWidth / 2, screenHeight / 2);
        gates = new ArrayList<>();

        // Add a mouse movement listener to the canvas
        canvas.setOnMouseMoved(event -> {
            // Update player's position to follow the mouse coordinates
            player.setPosition(event.getX(), event.getY(), border);
        });

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

        // Update gates and check for explosions
        for (Gate gate : gates) {
            gate.update(deltaTime);
            if (gate.isExploded()) {
                checkEnemiesNearGate(gate);  // Check if enemies are within explosion range
            }
        }

        // Update time since the last gate spawn
        timeSinceLastGate += deltaTime;

        // Spawn a new gate every 5 seconds
        if (timeSinceLastGate >= gateSpawnInterval) {
            spawnNewGate();
            timeSinceLastGate = 0; // Reset the timer
        }

        // Check for collisions and explode gates
        Iterator<Gate> gateIterator = gates.iterator();
        while (gateIterator.hasNext()) {
            Gate gate = gateIterator.next();
            if (gate.checkCollisionWithPlayer(player)) {
                gate.explode(); // Trigger the explosion
                gateIterator.remove(); // Remove the gate after explosion
            }
        }

        // Update enemies
        for (Enemy enemy : enemies) {
            enemy.update(deltaTime);
        }

        // Track time and spawn enemies every second
        enemySpawnTime += deltaTime;
        if (enemySpawnTime >= enemySpawnInterval) {
            // Spawn new enemy at a random location
            double enemyX = Math.random() * screenWidth;
            double enemyY = Math.random() * screenHeight;
            enemies.add(new Enemy(enemyX, enemyY, player));

            // Reset the spawn time counter
            enemySpawnTime = 0;
        }
    }

    // Method to check if any enemies are within the explosion radius of a gate
    private void checkEnemiesNearGate(Gate gate) {
        List<Enemy> enemiesToRemove = new ArrayList<>(); // Store enemies to remove after explosion
        for (Enemy enemy : enemies) {
            // Check if the enemy is within the updated 100-pixel explosion radius
            if (isEnemyNearGate(enemy, gate)) {
                // Trigger enemy explosion immediately
                enemy.explode();
                enemiesToRemove.add(enemy);  // Mark the enemy for immediate removal
            }
        }
        // Remove all enemies that were within the explosion radius
        enemies.removeAll(enemiesToRemove);
    }

    // Method to check if an enemy is within 100 pixels of a gate's edge
    private boolean isEnemyNearGate(Enemy enemy, Gate gate) {
        // Get gate bounds
        double gateMinX = gate.getX() - 50;
        double gateMaxX = gate.getX() + 50;
        double gateMinY = gate.getY() - 50;
        double gateMaxY = gate.getY() + 50;

        // Get enemy position
        double enemyX = enemy.getX();
        double enemyY = enemy.getY();

        return enemyX > gateMinX && enemyX < gateMaxX || enemyY > gateMinY && enemyY < gateMaxY;
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

        // Render all gates
        for (Gate gate : gates) {
            gate.render(gc);
        }

        // Render all enemies
        for (Enemy enemy : enemies) {
            enemy.render(gc);
        }
    }

    // Spawn a new gate at a random position within the border
    private void spawnNewGate() {
        // Define the safe boundaries for gate spawning inside the border
        double minX = border.getX() + Gate.getGateWidth() / 2;
        double maxX = border.getX() + border.getWidth() - Gate.getGateWidth() / 2;
        double minY = border.getY() + Gate.getGateHeight() / 2;
        double maxY = border.getY() + border.getHeight() - Gate.getGateHeight() / 2;

        // Generate random coordinates within those boundaries
        double randomX = minX + random.nextDouble() * (maxX - minX);
        double randomY = minY + random.nextDouble() * (maxY - minY);

        // Create a new Gate object and add it to the list of gates
        gates.add(new Gate(randomX, randomY));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

