package cs1622.projecttwo.pacifistgeometrygame;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

/**
 * Border class defines the border and the render function to allow for the border to be rendered in game
 */
public class Border extends GameObject {
    private static double boxWidth = 1200;
    private static double boxHeight = 700;

    public Border(double x, double y) {
        super(x, y, boxWidth, boxHeight);
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void explode() {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, boxWidth, boxHeight);

        gc.setStroke(Color.CYAN);
        gc.setLineWidth(10);
        gc.strokeRect(x, y, boxWidth, boxHeight);
    }

    // Getter for the border width
    public double getWidth() {
        return boxWidth;
    }

    // Getter for the border height
    public double getHeight() {
        return boxHeight;
    }
}
