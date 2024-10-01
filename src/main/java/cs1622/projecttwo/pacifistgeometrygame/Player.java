package cs1622.projecttwo.pacifistgeometrygame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Player class extending GameObject
public class Player extends GameObject {
    private double speed;
    private static final double circleRadius = 10;


    public Player(double x, double y, double speed) {
        // Initialize position and size
        super(x, y, circleRadius, circleRadius);
        this.speed = speed;
    }

    // Method to update player's position
    @Override
    public void update(double deltaTime) {
        handleMovement(deltaTime);

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

    // Handle player movement based on input (simple WASD or arrow key logic)
    private void handleMovement(double deltaTime) {
        // In a full game, you would read input here (e.g., key presses for movement)
        // For example, if you implement key listeners:
        // if (upKeyPressed) { y -= speed * deltaTime; }
        // if (downKeyPressed) { y += speed * deltaTime; }
        // if (leftKeyPressed) { x -= speed * deltaTime; }
        // if (rightKeyPressed) { x += speed * deltaTime; }

        // TODO: Implement actual input handling here
    }

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