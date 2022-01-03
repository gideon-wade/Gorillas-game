package ApplicationClasses;
import java.lang.*;

public class banana {
    private int velocity;
    private double gravity;
    private int angle;

public banana(int velocity, double gravity, int angle){
    this.velocity = velocity;
    this.gravity = gravity;
    this.angle = angle;

}

public int trajectory(int i){

    return (int) (gravity/(2*velocity^2)*(Math.cos(angle)))*i^2+ (int) Math.tan(angle)*i;

}



}
