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
    private static Game gamer;
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
    public ImageView monkeyOne;
    public ImageView monkeyTwo;

    private int playerOneAngle;
    private int playerOneVelocity;
    private int playerTwoAngle;
    private int playerTwoVelocity;
    private List<Integer> list = new ArrayList<>();


    public void goToMainScene() throws IOException {
        SceneManager.changeScene("fxml/MainScene.fxml");
    }

    //Game gamer = new Game("Søren","Gucci",800,1300);

    // A grid that is false everywhere on the scene, except the buildings and monkeys where it is true.



    public void doThrow(ActionEvent event) throws IOException {
        System.out.println(gamer.player1.getName());
        if (gamer.player1.getTurn()) {
            this.playerOneAngle = Integer.parseInt(pl1ang.getText());
            this.playerOneVelocity = Integer.parseInt(pl1vec.getText());
            System.out.println(playerOneAngle);
        } else {
            this.playerTwoAngle = Integer.parseInt(pl2ang.getText());
            this.playerTwoVelocity = Integer.parseInt(pl2vec.getText());
            System.out.println(playerTwoAngle);
        }
        boolean arr[][] = new boolean[10][10];

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


            for (int i = 0; i < list.size(); i++) {
                banana.setX(i);
                banana.setY(list.get(i));
                banana.isSmooth();
                simulateSlow();
            }

            switchVisibility();

            gamer.player1.setTurn(false);

        } else {
            System.out.println("Passed else");
            banana.setX(1200);
            banana.setY(100);
            banana.setVisible(true);
            System.out.println("pl2vel: " + playerTwoVelocity);
            System.out.println("pl2ang: " + playerTwoAngle);
            Banana banan = new Banana(playerTwoVelocity, 9.82, playerTwoAngle);

            list = makeCurve(banan);



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
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Integer> makeCurve(Banana banan){
        int x = 1;
        while (banan.trajectory(x) > -1) {
            this.list.add(100 - banan.trajectory(x));
            banana.isSmooth();
            x++;
        }
        return this.list;
    }

    public void switchVisibility() {
        pl1ang.setVisible(!pl1ang.isVisible());
        pl1vec.setVisible(!pl1vec.isVisible());
        pl1anglabel.setVisible(!pl1anglabel.isVisible());
        pl1vellabel.setVisible(!pl1vellabel.isVisible());

        pl2ang.setVisible(!pl2ang.isVisible());
        pl2vec.setVisible(!pl2vec.isVisible());
        pl2anglabel.setVisible(!pl2anglabel.isVisible());
        pl2vellabel.setVisible(!pl2vellabel.isVisible());
    }
    /*
    public void setName() {
        nameLabel1.setText(MainScene.playerOneName);
        nameLabel2.setText(MainScene.playerTwoName);
    }*/

    public void showCurve(List list){ // Not needed for final project, but helps to get an insight
        String s = "";

        for (int i = 0; i < list.size(); i++) {
            s += " " + list.get(i);
        }
        System.out.println(s);
    }
    public static void setGame(Game game){
        gamer = game;
    }
}