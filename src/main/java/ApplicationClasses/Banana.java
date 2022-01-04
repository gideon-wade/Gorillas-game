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

        y = (int)(-(9.82 * Math.pow(x, 2)) / (2 * Math.pow(30, 2) * Math.pow(Math.cos(45), 2)) + Math.tan(45) * x);

        return y;
    }
}