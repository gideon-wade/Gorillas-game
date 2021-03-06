package ApplicationClasses;
import java.lang.*;

public class Banana {
    private int velocity;
    private double gravity;
    private int angle;

    public Banana(int velocity, double gravity, int angle){
        this.velocity = velocity;
        this.gravity = gravity;
        this.angle = angle;
    }

    public int trajectory(int x) {
        return (int) (-(gravity * Math.pow(x, 2)) /
                (2 * Math.pow(velocity, 2) * Math.pow(Math.cos(Math.toRadians(angle)), 2))
                + Math.abs(Math.tan(Math.toRadians(angle))) * x);
    }
}