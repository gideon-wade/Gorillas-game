package ApplicationClasses;

public class Game {
    private Player player1;
    private Player player2;
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


}
