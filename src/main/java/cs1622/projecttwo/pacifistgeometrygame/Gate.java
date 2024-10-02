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

    // Constructor
    public Gate(double x, double y) {
        super(x, y, widthOfGateRect, heightOfGateRect);
    }

    // Method to update gate's position
    @Override
    public void update(double deltaTime) {
    }

    public void render(GraphicsContext gc) {
        // Set lightgreen fill
        gc.setFill(Color.LIGHTGREEN);

        // Fill rect at that position
        gc.fillRect(x - widthOfGateRect, y - heightOfGateRect, widthOfGateRect, heightOfGateRect);
    }
}
