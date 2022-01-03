package ApplicationClasses;

public class Player {
    int[] starting_position = new int[2];

    public Player(int position) {
        starting_position[0] = position;
    }

    public void throwing(int velocity, double gravity, int angle) {
        Banana banan = new Banana(velocity, gravity, angle);
        banan.trajectory();
    }
}