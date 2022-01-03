package ApplicationClasses;

public class Player {
    final int[] starting_position = new int[2];
    final String name;

    public Player(int position, String name) {
        starting_position[0] = position;
        this.name = name;
    }

    public void throwing(int velocity, double gravity, int angle) {
        Banana banan = new Banana(velocity, gravity, angle);
        banan.trajectory();
    }
}