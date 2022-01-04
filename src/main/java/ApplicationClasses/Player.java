package ApplicationClasses;

public class Player {
    private int[] starting_position = new int[2];
    private String name;
    private Boolean theirTurn;

    public Player(int positionX, int positionY, String name) {
        starting_position[0] = positionX;
        starting_position[1] = positionY;
        this.name = name;
    }
    /*
    public void throwing(int velocity, double gravity, int angle) {
        Banana banana = new Banana(velocity, gravity, angle);
        int[]pos = banana.trajectory();

        var x = pos[0];
        var y = pos[1];
    }*/
    public void setTurn(Boolean turn){
        this.theirTurn = turn;
    }
    public Boolean getTurn() {return theirTurn;}
}