package ApplicationClasses;

import Controllers.MainScene;

public class Game {
    public Player player1;
    public Player player2;
    private int height;
    private int length;


    public Game(String player1Name, String player2Name, int height, int length){
        this.height = height;
        this.length = length;
        player1 = new Player(0,0, player1Name);
        player2 = new Player(length - 1, 0, player2Name);
        player1.setTurn(true);
        player2.setTurn(false);
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }


    // A grid that is false everywhere on the scene, except the buildings and monkeys where it is true.
    public static void grid(int rows, int columns) {
        boolean arr[][] = new boolean[rows][columns];
    }
}