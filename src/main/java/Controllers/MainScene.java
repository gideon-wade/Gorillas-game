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

    public void goToGameScreen() throws IOException {
        Game game = new Game("Søren",
                "Paul Fischer", 800, 1300);
        GameScreen gc = new GameScreen(game);
        SceneManager.changeScene("fxml/GameScreen.fxml");
    }
}
