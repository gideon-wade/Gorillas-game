package ApplicationClasses;

public class Player {
    private String name;
    private Boolean theirTurn;


    public Player(String name) {
        this.name = name;
    }


    public Boolean getTurn() {
        return theirTurn;
    }

    public String getName() {
        return name;
    }

    public void setTurn(Boolean turn){
        this.theirTurn = turn;
    }
}
