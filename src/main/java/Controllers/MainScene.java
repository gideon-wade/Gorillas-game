package Controllers;

import ApplicationClasses.Game;
import Exceptions.IllegalInputException;
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


    private int length_i;
    private int height_i;
    public String playerOneName;
    public String playerTwoName;
    private Game game;

    public void goToGameScreen() throws IOException, IllegalInputException {
        this.length_i = Integer.parseInt(length.getText());
        this.height_i = Integer.parseInt(height.getText());
        this.playerOneName = playerID1.getText();
        this.playerTwoName = playerID2.getText();
        if (height_i > 0 && length_i > 0 && height_i <= 800 && length_i <= 1300) {
            this.game = new Game(playerOneName, playerTwoName,
                    height_i, length_i);
        } else {
            throw new IllegalInputException("Height must be between 0 and 800, " +
                    "and length between 0 and 1300");
        }
        GameScreen.setGame(game);
        SceneManager.changeScene("fxml/GameScreen.fxml");
    }
}