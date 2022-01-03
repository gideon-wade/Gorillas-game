package ApplicationClasses;

public class Player {
    private int[] starting_position = new int[2];
    private String name;

    public Player(int positionX, int positionY, String name) {
        starting_position[0] = positionX;
        starting_position[1] = positionY;
        this.name = name;
    }

    public void throwing(int velocity, double gravity, int angle) {
        Banana banan = new Banana(velocity, gravity, angle);
        banan.trajectory(5);
    }
}