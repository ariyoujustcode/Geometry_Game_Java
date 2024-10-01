package cs1622.projecttwo.pacifistgeometrygame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Border class for rendering the game border.
 */
public class Border extends GameObject {
    private final double thickness; // Thickness of the border
    private final Color borderColor; // Color of the border
    private final double margin; // Margin around the border


    // Constructor
    public Border(double x, double y, double width, double height, double thickness, Color borderColor, double margin) {
        super(x, y, width, height); // Call GameObject constructor
        this.thickness = thickness;
        this.borderColor = borderColor;
        this.margin = margin;
    }

    // Update border function (not used)
    @Override
    public void update(double deltaTime) {
        // Border does not need to be updated
    }

    // Unique render of border
    @Override
    public void render(GraphicsContext gc) {
        // Step 1: Fill the entire canvas with a black background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        // Step 2: Draw the black interior of the rectangle (the box itself)
        gc.setFill(Color.BLACK); // Keep it black for the interior
        gc.fillRect(margin, margin, width - 2 * margin, height - 2 * margin);

        // Step 3: Draw the cyan border (stroke) around the rectangle
        gc.setStroke(Color.CYAN);
        gc.setLineWidth(thickness); // Set the border thickness
        gc.strokeRect(margin, margin, width - 2 * margin, height - 2 * margin);

    }
}

