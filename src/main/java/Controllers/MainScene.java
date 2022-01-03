package Controllers;

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
        SceneManager.changeScene("fxml/GameScreen.fxml");
    }
}
