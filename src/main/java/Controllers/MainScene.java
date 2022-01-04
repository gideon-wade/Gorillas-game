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

import java.io.IOException;

public class MainScene {

    @FXML
    public Button btnChange;
    @FXML
    public TextField length;
    @FXML
    public TextField height;

    @FXML
    public Image banana;

    private int length_i;
    private int height_i;

    public void goToGameScreen() throws IOException {
        Game game = new Game("Søren",
                "Paul Fischer", 800, 1300);
        GameScreen.setGame(game);
        System.out.println(length_i);
        SceneManager.changeScene("fxml/GameScreen.fxml");
        SceneManager.changeSize(height_i,length_i);
        ImageView iv1 = new ImageView(banana);
        iv1.setX(400);
    }

    public void saveLength(ActionEvent event) throws IOException {
            this.length_i = Integer.parseInt(length.getText());
    }

    public void saveHeight(ActionEvent event) throws IOException {
        this.height_i = Integer.parseInt(height.getText());
    }
}
