package ApplicationClasses;

import Exceptions.IllegalInputException;

public class World {
    private int height;
    private int width;
    private Monkey monkey1;
    private Monkey monkey2;
    private boolean[][] canHitGrid;

    public World(int height, int width) throws IllegalInputException {
        this.height = height;
        this.width = width;
        this.monkey1 = new Monkey(calculatePosition(1), calculatePosition(2),
                800 - 92, 800);
        this.monkey2 = new Monkey(calculatePosition(3),
                calculatePosition(4) , 800 - 92, 800);
        canHitGrid = new boolean[800][1300];
        makeGround();
        makeWorld();
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
    public boolean[][] getCantHitGrid() {
        return canHitGrid;
    }

    public void makeGround() {
        for (int i = 800 - 3; i < 800; i++) {
            for (int j = 0; j < 1300; j++) {
                canHitGrid[i][j] = true;
            }
        }
    }

    public void makeWorld(){
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 1300; j++) {
                if (j < monkey1.getStart_x()) {
                    canHitGrid[i][j] = true;
                }
                if (j > monkey2.getEnd_x()) {
                    canHitGrid[i][j] = true;
                }
            }
        }
    }

    public int calculatePosition(int x) throws IllegalInputException{
            switch (x) {
                case 1:
                    return (1300 - width) / 2;
                case 2:
                    return ((1300 - width) / 2) + 118;
                case 3:
                    return 1300 - 118 - (1300 - width) / 2;
                case 4:
                    return 1300 - (1300 - width) / 2;
            }
            throw new IllegalInputException("Only takes values from 1 to 4");
    }
}
