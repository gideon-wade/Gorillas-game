package ApplicationClasses;

public class World {
    private int height;
    private int width;
    private Monkey monkey1;
    private Monkey monkey2;

    public World(int height, int width) {
        this.height = height;
        this.width = width;
        this.monkey1 = new Monkey(0, 118, height - 92, height);
        this.monkey2 = new Monkey(width - 118, width, height - 92, height);
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public Monkey getMonkey1() {
        return monkey1;
    }
    public Monkey getMonkey2() {
        return monkey2;
    }
}
