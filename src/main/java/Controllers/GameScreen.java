package Controllers;


import ApplicationClasses.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Controllers.MainScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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


    public void goToMainScene() throws IOException {
        banana.setX(400);
        banana.setY(400);
        SceneManager.changeScene("fxml/MainScene.fxml");
    }

    public static void setGame(Game game){

    }

    public void pl1SetAngle(ActionEvent actionEvent) {
        int playerOneAngle = Integer.parseInt(pl1ang.getText());
    }

    public void pl1SetVelocity(ActionEvent actionEvent) {
        int playerOneVelocity = Integer.parseInt(pl1vec.getText());
    }

    public void pl2SetAngle(ActionEvent actionEvent) {
        int playerTwoAngle = Integer.parseInt(pl2ang.getText());
    }

    public void pl2SetVelocity(ActionEvent actionEvent) {
        int playerTwoVelocity = Integer.parseInt(pl2vec.getText());
    }


    public void grid(int rows, int columns) {
        boolean arr[][] = new boolean[rows][columns];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++){
                arr[i][j] = false;
            }
        }

    }

}









