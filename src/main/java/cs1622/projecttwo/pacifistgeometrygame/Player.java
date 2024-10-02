package cs1622.projecttwo.pacifistgeometrygame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class for the Player of the game. Inherits traits and behavior from GameObject class.
 */
public class Player extends GameObject {
    // Radius of circle which is the player
    private static final double circleRadius = 10;

    /**
     * Constructor for Player that inherits its x and y values for it's positioning from GameObject and the radius
     * @param x
     * @param y
     */
    public Player(double x, double y) {
        // Initialize position and size
        super(x, y, circleRadius, circleRadius);
    }

    // Empty method to update player's position
    @Override
    public void update(double deltaTime) {
    }

    // Update player position based on mouse coordinates
    public void setPosition(double x, double y, Border border) {
        // Get the boundaries of the Border
        double minX = border.getX() + 5;
        double minY = border.getY() + 5;
        double maxX = minX + border.getWidth() - circleRadius * 2; // Subtracting player's width
        double maxY = minY + border.getHeight() - circleRadius * 2; // Subtracting player's height

        // Clamp the player's x position within the border
        if (x < minX + circleRadius) {
            this.x = minX + circleRadius;
        } else if (x > maxX) {
            this.x = maxX;
        } else {
            this.x = x;
        }

        // Clamp the player's y position within the border
        if (y < minY + circleRadius) {
            this.y = minY + circleRadius;
        } else if (y > maxY) {
            this.y = maxY;
        } else {
            this.y = y;
        }
    }

    // Get radius
    public static double getRadius() {
        return circleRadius;
    }
    @Override
    public void move() {
        // Player is moved with setPosition
    }

    @Override
    public void explode() {

    }

    // Render the player on the screen
    @Override
    public void render(GraphicsContext gc) {
        // Set the fill color for the player
        gc.setFill(Color.DARKORANGE);

        // Draw the circle at the player's position
        gc.fillOval(x - circleRadius, y - circleRadius, circleRadius * 2, circleRadius * 2); // Center the circle at (x, y)

        // Optionally, draw the outline of the circle
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        gc.strokeOval(x - circleRadius, y - circleRadius, circleRadius * 2, circleRadius * 2);
    }
}