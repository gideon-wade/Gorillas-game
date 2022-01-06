package Controllers;

import ApplicationClasses.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;

public class MainScene {


    @FXML
    public Button btnChange;
    @FXML
    public TextField length;
    @FXML
    public TextField height;
    public TextField playerID1;
    public TextField playerID2;


    private static Game game;

    private int length_i;
    private int height_i;
    public String playerOneName;
    public String playerTwoName;

    public void goToGameScreen() throws IOException {
        SceneManager.changeScene("fxml/GameScreen.fxml");
        this.game = new Game(playerOneName, playerTwoName,
                height_i, length_i);
        GameScreen.setGame(game);
        GameScreen.grid(length_i,height_i);
    }

    public void saveLength(ActionEvent event) throws IOException {
        this.length_i = Integer.parseInt(length.getText());
    }

    public void saveHeight(ActionEvent event) throws IOException {
        this.height_i = Integer.parseInt(height.getText());
    }

    public void saveName1(ActionEvent actionEvent) {
        this.playerOneName = playerID1.getText();
    }

    public void saveName2(ActionEvent actionEvent) {
        this.playerTwoName = playerID2.getText();
    }

    public static Game getGame(){
        return game;
    }
}