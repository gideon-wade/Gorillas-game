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

    public void goToGameScreen() throws IOException {
        Game game = new Game("Søren",
                "Paul Fischer", 800, 1300);
        GameScreen.setGame(game);
        SceneManager.changeScene("fxml/GameScreen.fxml");
    }

    public void saveLength(ActionEvent event) {
    }

    public void saveHeight(ActionEvent event) {
    }
}
