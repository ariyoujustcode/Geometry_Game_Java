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
        // Set the fill color for the inner area (e.g., a darker color or black)
        // gc.setFill(Color.CYAN); // Or any color you want for the inner area
        // gc.fillRect(x + margin, y + margin, width - margin * 2, height - margin * 2);

        // Set the stroke color for the border
        gc.setStroke(borderColor); // Set the border color
        gc.setLineWidth(thickness); // Set the border thickness

        // Draw the border outline
        gc.strokeRect(x + margin, y + margin, width - margin * 2, height - margin * 2);
    }
}

