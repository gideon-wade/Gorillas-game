package Controllers;


import ApplicationClasses.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GameScreen {
    public Game game;
    @FXML
    public Button btnBack;
    public TextField pl1ang;
    public TextField pl1vec;
    public TextField pl2ang;
    public TextField pl2vec;

    public GameScreen(Game game){
        this.game = game;
    }

    public void goToMainScene() throws IOException {
        SceneManager.changeScene("fxml/MainScene.fxml");
    }

    public void pl1SetAngle(ActionEvent actionEvent) {

    }

    public void pl1SetVelocity(ActionEvent actionEvent) {

    }

    public void pl2SetAngle(ActionEvent actionEvent) {

    }

    public void pl2SetVelocity(ActionEvent actionEvent) {

    }
}
