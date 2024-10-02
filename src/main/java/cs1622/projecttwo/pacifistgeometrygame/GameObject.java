package cs1622.projecttwo.pacifistgeometrygame;
import javafx.scene.canvas.GraphicsContext;
/**
 * Abstract object class to set up inheritance for actual game objects such as player, enemy and gate.
 */
public abstract class GameObject {
    protected double x, y; // Position
    protected double width, height; // Size

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Abstract methods for updating and rendering
    public abstract void update(double deltaTime);
    public abstract void render(GraphicsContext gc);

    // Explode
    public abstract void explode();

    // Movement
    public abstract void move();

    // Getter for x position
    public double getX() {
        return x;
    }

    // Getter for y position
    public double getY() {
        return y;
    }
}

