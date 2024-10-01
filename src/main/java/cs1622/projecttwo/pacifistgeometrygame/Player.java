package cs1622.projecttwo.pacifistgeometrygame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Example: Player class extending GameObject
public class Player extends GameObject {
    private double speed;
    private static final double TRIANGLE_SIZE = 100;


    public Player(double x, double y, double speed) {
        // Initialize position and size
        super(x, y, TRIANGLE_SIZE, TRIANGLE_SIZE);
        this.speed = speed;
    }

    // Method to update player's position and invincibility
    @Override
    public void update(double deltaTime) {
        handleMovement(deltaTime);

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
        // Render the player (e.g., a simple rectangle)
        gc.setFill(Color.DARKORANGE);

        // Calculate the vertices of the equilateral triangle based on position (x, y)
        double halfBase = TRIANGLE_SIZE / 2;
        double height = Math.sqrt(3) / 2 * TRIANGLE_SIZE; // Height of the triangle

        double[] xPoints = {x, x - halfBase, x + halfBase};
        double[] yPoints = {y - height / 2, y + height / 2, y + height / 2};

        gc.fillPolygon(xPoints, yPoints, 3); // Draw the triangle
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
    }
}