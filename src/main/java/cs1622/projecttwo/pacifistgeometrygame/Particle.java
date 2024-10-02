package cs1622.projecttwo.pacifistgeometrygame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents individual particles in the gate explosion effect.
 */
public class Particle extends GameObject {
    private double speed;
    private double angle;  // Direction in degrees
    protected double opacity = 1.0;
    private static double radius = 5.0;

    // Constructor
    public Particle(double x, double y, double speed, double angle) {
        super(x, y, radius, radius);
        this.speed = speed;
        this.angle = angle;
    }

    @Override
    public void move() {
        // Empty
    }
    @Override
    public void explode() {

    }

    // Update particle movement and opacity
    @Override
    public void update(double deltaTime) {
        double radians = Math.toRadians(angle);
        x += speed * Math.cos(radians) * deltaTime;
        y += speed * Math.sin(radians) * deltaTime;
        opacity -= 0.05;  // Fade out
    }

    // Render particle
    @Override
    public void render(GraphicsContext gc) {
        gc.setGlobalAlpha(opacity);  // Apply fade-out effect
        gc.setFill(Color.LIGHTGREEN);
        gc.fillOval(x - radius / 2, y - radius / 2, radius, radius);
        gc.setGlobalAlpha(1.0);  // Reset opacity to default
    }
}
