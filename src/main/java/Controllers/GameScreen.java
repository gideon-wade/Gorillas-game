package Controllers;


import ApplicationClasses.Banana;
import ApplicationClasses.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;

public class GameScreen<playerOneAngle> {
    public Game game;
    @FXML
    public Button btnBack;
    public TextField pl1ang;
    public TextField pl1vec;
    public TextField pl2ang;
    public TextField pl2vec;
    public ImageView banana;
    public Button Button;


    public void goToMainScene() throws IOException {
        banana.setX(400);
        banana.setY(400);
        SceneManager.changeScene("fxml/MainScene.fxml");
    }


    public static void setGame(Game game){

    }

    private int playerOneAngle;
    private int playerOneVelocity;

    public void pl1SetAngle(ActionEvent event) throws IOException {
        this.playerOneAngle = Integer.parseInt(pl1ang.getText());
    }


    public void pl1SetVelocity(ActionEvent event) throws IOException {
        this.playerOneVelocity = Integer.parseInt(pl1vec.getText());
    }

    public void pl2SetAngle(ActionEvent actionEvent) {
        int playerTwoAngle = Integer.parseInt(pl2ang.getText());
    }

    public void pl2SetVelocity(ActionEvent actionEvent) {
        int playerTwoVelocity = Integer.parseInt(pl2vec.getText());
    }

// A grid that is false everywhere on the scene, except the buildings and monkeys where it is true.
    public void grid(int rows, int columns) {
        boolean arr[][] = new boolean[rows][columns];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++){
                arr[i][j] = false;
            }
        }

    }

    public void touchMe(ActionEvent event) throws IOException {
        Thread thread = new Thread(this::runThread);
        thread.start();
    }

    public void runThread() {
        banana.setX(1);
        banana.setY(100);

        Banana banan = new Banana(this.playerOneVelocity, 9.82, this.playerOneAngle);
        int x = 1;

        while (banana.getY() <= 100) {
            banana.setX(x);
            banana.setY(100 - banan.trajectory(x));
            banana.isSmooth();
            simulateSlow(5);
            System.out.println(banana.getY());
            x++;
        }

        simulateSlow(1000);
        banana.setVisible(false);
    }

    public void simulateSlow(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}









