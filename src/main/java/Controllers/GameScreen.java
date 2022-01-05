package Controllers;


import ApplicationClasses.Banana;
import ApplicationClasses.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameScreen {
    public Game game;
    @FXML
    public Button btnBack;
    public TextField pl1ang;
    public TextField pl1vec;
    public TextField pl2ang;
    public TextField pl2vec;
    public ImageView banana;
    public Button Button;
    public Label pl2anglabel;
    public Label pl2vellabel;
    public Label pl1anglabel;
    public Label pl1vellabel;
    public Label nameLabel1;
    public Label nameLabel2;

    private int playerOneAngle;
    private int playerOneVelocity;
    private int playerTwoAngle;
    private int playerTwoVelocity;
    private List<Integer> list = new ArrayList<>();


    public void goToMainScene() throws IOException {
        SceneManager.changeScene("fxml/MainScene.fxml");
    }

    Game gamer = new Game("Søren","Gucci",800,1300);



    // A grid that is false everywhere on the scene, except the buildings and monkeys where it is true.
    public void grid(int rows, int columns) {
        boolean arr[][] = new boolean[rows][columns];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++){
                arr[i][j] = false;
            }
        }
    }

    public void doThrow(ActionEvent event) throws IOException {
        setName();
        if (gamer.player1.getTurn()) {
            this.playerOneAngle = Integer.parseInt(pl1ang.getText());
            this.playerOneVelocity = Integer.parseInt(pl1vec.getText());
            System.out.println(playerOneAngle);
        } else {
            this.playerTwoAngle = Integer.parseInt(pl2ang.getText());
            this.playerTwoVelocity = Integer.parseInt(pl2vec.getText());
            System.out.println(playerTwoAngle);
        }
        Thread thread = new Thread(this::runThread);
        thread.start();
    }

    public void runThread() {
        list = new ArrayList<>();
        if (gamer.player1.getTurn()) {
            banana.setX(1);
            banana.setY(100);
            banana.setVisible(true);

            Banana banan = new Banana(playerOneVelocity, 9.82, playerOneAngle);
            list = makeCurve(banan);
            String s = "";
            for (int i = 0; i < list.size(); i++) {
                s += " " + list.get(i);
            }
            System.out.println(s);


            for (int i = 0; i < list.size(); i++) {
                banana.setX(i);
                banana.setY(list.get(i));
                banana.isSmooth();
                simulateSlow();
            }

            switchVisibility();

            gamer.player1.setTurn(false);

        } else {
            banana.setX(1200);
            banana.setY(100);
            banana.setVisible(true);

            Banana banan = new Banana(playerTwoVelocity, 9.82, playerTwoAngle);
            list = makeCurve(banan);

            String s = "";
            for (int i = 0; i < list.size(); i++) {
                s += " " + list.get(i);
            }
            System.out.println(s);

            for (int i = 0; i < list.size(); i++) {
                banana.setX(1200 - i);
                banana.setY(list.get(list.size() - 1 - i));
                banana.isSmooth();
                simulateSlow();
            }

            switchVisibility();

            gamer.player1.setTurn(true);


        }
        simulateSlow();
        banana.setVisible(false);
    }

    public void simulateSlow() {
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Integer> makeCurve(Banana banan){
        int x = 1;

        while (banan.trajectory(x) > 0) {
            this.list.add(100 - banan.trajectory(x));
            banana.isSmooth();
            x++;
        }
        return this.list;
    }
    public void switchVisibility() {
        if (gamer.player1.getTurn()){
            pl2ang.setVisible(true);
            pl2vec.setVisible(true);
            pl2anglabel.setVisible(true);
            pl2vellabel.setVisible(true);

            pl1ang.setVisible(false);
            pl1vec.setVisible(false);
            pl1anglabel.setVisible(false);
            pl1vellabel.setVisible(false);
        } else {
            pl1ang.setVisible(true);
            pl1vec.setVisible(true);
            pl1anglabel.setVisible(true);
            pl1vellabel.setVisible(true);

            pl2ang.setVisible(false);
            pl2vec.setVisible(false);
            pl2anglabel.setVisible(false);
            pl2vellabel.setVisible(false);
        }
    }

    public void setName() {
        nameLabel1.setText(MainScene.playerOneName);
        nameLabel2.setText(MainScene.playerTwoName);
    }
}