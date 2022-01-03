package ApplicationClasses;

public class Player {
    int[] starting_position = new int[2];

    public Player(int position) {
        starting_position[0] = position;
    }

    public void throw(int velocity, int angle) {
        Banana banan = new Banana();
        banan.trajectory(velocity, angle);
    }
}
