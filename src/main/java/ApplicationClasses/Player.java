package ApplicationClasses;

public class Player {
    private String name;
    private Boolean theirTurn;
    private Monkey monkey;


    public Player(String name) {
        this.name = name;
    }

    public void setTurn(Boolean turn){
        this.theirTurn = turn;
    }
    public Boolean getTurn() {
        return theirTurn;
    }

    public String getName() {
        return name;
    }

    public Monkey getMonkey() {
        return monkey;
    }

    public void setMonkey(Monkey monkey) {
        this.monkey = monkey;
    }
}
