package cs1622.projecttwo.pacifistgeometrygame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Define the enemy, it's attributes and behaviors such as how fast they move, how and when they respawn
 * and when they explode.
 */
public class Enemy extends GameObject {
    // Variables
    private static final double squareWidth = 15;
    private static final double squareHeight = 15;

    // Constructor
    public Enemy(double x, double y) {
        super(x, y, squareWidth, squareHeight);
    }

    // Enemy class logic
    @Override
    public void update(double deltaTime) {

    }

    // Move enemies
    @Override
    public void move() {

    }

    @Override
    public void render(GraphicsContext gc) {
        // Set blue fill
        gc.setFill(Color.SKYBLUE);
        gc.fillRect(x - squareWidth, y - squareHeight, squareWidth, squareHeight);

    }

    @Override
    public void explode() {

    }
}
