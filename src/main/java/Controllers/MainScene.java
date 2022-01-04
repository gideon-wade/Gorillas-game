package Controllers;

import ApplicationClasses.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainScene {

    @FXML
    public Button btnChange;
    public TextField length;
    public TextField height;

    int length_i;
    int height_i;

    public void goToGameScreen() throws IOException {
        Game game = new Game("Søren",
                "Paul Fischer", 800, 1300);
        GameScreen.setGame(game);
        SceneManager.changeScene("fxml/GameScreen.fxml");
        SceneManager.changeSize(height_i,length_i);
    }

    public void saveLength() throws IOException {
        int length_1 = Integer.parseInt(length.getText());
    }

    public void saveHeight() throws IOException {
        int height_1 = Integer.parseInt(height.getText());
    }
}
