package ApplicationClasses;

public class Banana {
    private int velocity;
    private double gravity;
    private int angle;

    public Banana(int velocity, double gravity, int angle){
        this.velocity = velocity;
        this.gravity = gravity;
        this.angle = angle;
    }

    public int trajectory(){
        return 2;
    }
}