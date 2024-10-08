package cs1622.projecttwo.pacifistgeometrygame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Define the enemy, its attributes, and behaviors such as how fast they move, how and when they respawn,
 * and when they explode.
 */
public class Enemy extends GameObject {
    private static final double squareWidth = 15;
    private static final double squareHeight = 15;
    private static final double SPEED = 100; // Enemy speed in pixels per second
    private boolean exploded = false; // Explosion state
    private Player player;
    private double opacity = 1.0;
    private double expansionSpeed = 50.0;

    // Constructor
    public Enemy(double x, double y, Player player) {
        super(x, y, squareWidth, squareHeight);
        this.player = player; // Reference to the player object
    }

    // Handle enemy explosion, setting it into the explosion state
    @Override
    public void explode() {
        exploded = true;  // Mark the enemy as exploded
    }

    // Update enemy movement towards the player
    @Override
    public void update(double deltaTime) {
        if (!exploded) {
            moveTowardsPlayer(deltaTime);
        }
    }

    /**
     * Move the enemies towards the player's position
     * @param deltaTime
     */
    private void moveTowardsPlayer(double deltaTime) {
        // Calculate direction towards the player
        double dirX = player.getX() - this.x;
        double dirY = player.getY() - this.y;

        // Calculate distance to normalize the direction vector
        double distance = Math.sqrt(dirX * dirX + dirY * dirY);
        dirX /= distance; // Normalize X direction
        dirY /= distance; // Normalize Y direction

        // Move the enemy towards the player
        this.x += dirX * SPEED * deltaTime;
        this.y += dirY * SPEED * deltaTime;
    }

    // Render the enemy on the screen
    @Override
    public void render(GraphicsContext gc) {
        if (!exploded) {
            // Render normal enemy
            gc.setFill(Color.SKYBLUE);
            gc.fillRect(x - squareWidth / 2, y - squareHeight / 2, squareWidth, squareHeight);
        } else {
            // Expansion and fade-out effect
            opacity -= 0.02;  // Reduce opacity over time
            if (opacity > 0) {
                gc.setGlobalAlpha(opacity);
                gc.setFill(Color.SKYBLUE);
                gc.fillRect(x - squareWidth / 2, y - squareHeight / 2, squareWidth + expansionSpeed, squareHeight + expansionSpeed);
                gc.setGlobalAlpha(1.0);  // Reset opacity to default
            }
        }
    }
}

