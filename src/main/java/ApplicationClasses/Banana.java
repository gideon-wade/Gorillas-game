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
        int y = 1;

        y = (int)(-(gravity * Math.pow(x, 2)) /
                (2 * Math.pow(velocity, 2) * Math.pow(Math.cos(angle), 2)) + Math.tan(angle) * x);
        return y;

    }
}