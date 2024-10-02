package cs1622.projecttwo.pacifistgeometrygame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Create gates that spawn to allow the player to move through them to destroy the nearby enemies.
 */

public class Gate extends GameObject {
    // Variables for gate
    private static final double widthOfGateRect = 60;
    private static final double heightOfGateRect = 2;
    private double opacity = 1.0;
    private double expansionSpeed = 50.0;
    private List<Particle> particles; // Particles for the burst effect
    private Random random = new Random();

    // Not blown up yet
    private boolean exploded = false;

    // Constructor
    public Gate(double x, double y) {
        super(x, y, widthOfGateRect, heightOfGateRect);
        particles = new ArrayList<>();
    }

    // Method to update gate's position
    @Override
    public void update(double deltaTime) {
        if (exploded) {
            expansionSpeed += 100 * deltaTime;  // Expand gate faster
            for (Particle particle : particles) {
                particle.update(deltaTime);
            }
        }
    }

    @Override
    public void move() {
        // Gate movements
    }

    // Check if the gate is touching the player (circle collision detection)
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
        createParticles(); // Explode effect
    }

    // Create particle burst for the explosion
    private void createParticles() {
        int numParticles = 20;  // Number of particles
        for (int i = 0; i < numParticles; i++) {
            double speed = random.nextDouble() * 100 + 50; // Speed of particles
            double angle = random.nextDouble() * 360; // Direction of particles
            particles.add(new Particle(x, y, angle, speed));
        }
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

            // Render particles
            for (Particle particle : particles) {
                particle.render(gc);
            }

            // Remove particles after they fade out
            particles.removeIf(particle -> particle.opacity <= 0);
        }
    }

    public static double getGateWidth() {
        return widthOfGateRect;
    }

    public static double getGateHeight() {
        return heightOfGateRect;
    }
}
