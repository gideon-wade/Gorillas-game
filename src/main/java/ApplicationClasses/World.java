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
                1000 - 92, 1000);
        this.monkey2 = new Monkey(calculatePosition(3),
                calculatePosition(4) , 1000 - 92, 1000);
        canHitGrid = new boolean[1000][1700];
        makeGround();
        makeWorld();
        System.out.println(monkey1.getStart_x());
        System.out.println(monkey2.getEnd_x());
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
        for (int i = 1000 - 1; i < 1000; i++) {
            for (int j = 0; j < 1700; j++) {
                canHitGrid[i][j] = true;
            }
        }
    }

    public void makeWorld(){
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1700; j++) {
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
                    return (1700 - width) / 2;
                case 2:
                    return ((1700 - width) / 2) + 118;
                case 3:
                    return 1700 - 118 - (1700 - width) / 2;
                case 4:
                    return 1700 - (1700 - width) / 2;
            }
            throw new IllegalInputException("Only takes values from 1 to 4");
    }

    public void hitBox(Player player) {
        if (!player.getTurn()) {
            for (int i = monkey1.getStart_y(); i < monkey1.getEnd_y(); i++) {
                for (int k = monkey1.getStart_x(); k < monkey1.getEnd_x(); k++) {
                    if (i >= 0 && k >= 0 && i < height && k < width) {
                        canHitGrid[i][k] = true;
                    }
                }
            }
        } else {
            for (int i = monkey2.getStart_y(); i < monkey2.getEnd_y(); i++) {
                for (int k = monkey2.getStart_x(); k < monkey2.getEnd_x(); k++) {
                    if(i >= 0 && k >= 0 && i < height && k < width) {
                        canHitGrid[i][k] = true;
                    }
                }
            }
        }
    }
}
