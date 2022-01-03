package ApplicationClasses;

public class Player {
    int[] starting_position = new int[2];

    public Player(int positionX, int positionY) {
        starting_position[0] = positionX;
        starting_position[1] = positionY;
    }

    public void throwing(int velocity, double gravity, int angle) {
        Banana banana = new Banana(velocity, gravity, angle);
        banana.trajectory();
    }
}