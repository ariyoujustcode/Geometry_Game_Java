package cs1622.projecttwo.pacifistgeometrygame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Create gates that spawn to allow the player to move through them to destroy the nearby enemies.
 */
public class Gate extends GameObject {
    // Variables for gate
    private static final double widthOfGateRect = 60;
    private static final double heightOfGateRect = 2;
    private double opacity = 1.0;
    private double expansionSpeed = 50.0;

    // Not blown up yet
    private boolean exploded = false;

    // Constructor
    public Gate(double x, double y) {
        super(x, y, widthOfGateRect, heightOfGateRect);
    }

    // Method to update gate's position
    @Override
    public void update(double deltaTime) {
        if (exploded) {
            expansionSpeed += 100 * deltaTime;  // Expand gate faster
        }
    }

    /**
     * Check if player ran through the gate
     * @param player
     * @return
     */
    public boolean checkCollisionWithPlayer(Player player) {
        double playerX = player.getX();
        double playerY = player.getY();
        double playerRadius = player.getRadius();


        // Simple rectangular collision detection between the gate and the player
        return (playerX + playerRadius > x - widthOfGateRect / 2 &&
                playerX - playerRadius < x + widthOfGateRect / 2 &&
                playerY + playerRadius > y - heightOfGateRect / 2 &&
                playerY - playerRadius < y + heightOfGateRect / 2);
    }

    // Explode gate when Player moves through and triggers event
    @Override
    public void explode() {
        exploded = true; // Exploded
    }

    public boolean isExploded() {
        return exploded;
    }

    public void render(GraphicsContext gc) {
        if (!exploded) {
            // Normal gate rendering
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(x - widthOfGateRect / 2, y - heightOfGateRect / 2, widthOfGateRect, heightOfGateRect);
        } else {
            // Expansion and fade-out effect
            opacity -= 0.02;  // Reduce opacity over time
            if (opacity > 0) {
                gc.setGlobalAlpha(opacity);
                gc.setFill(Color.LIGHTGREEN);
                gc.fillRect(x - widthOfGateRect / 2, y - heightOfGateRect / 2, widthOfGateRect + expansionSpeed, heightOfGateRect + expansionSpeed);
                gc.setGlobalAlpha(1.0);  // Reset opacity to default
            }
        }
    }

    public static double getGateWidth() {
        return widthOfGateRect;
    }

    public static double getGateHeight() {
        return heightOfGateRect;
    }
}
