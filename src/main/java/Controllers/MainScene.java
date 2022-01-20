package Controllers;

import ApplicationClasses.Game;
import Exceptions.IllegalInputException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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


    private int width_i;
    private int height_i;
    public String playerOneName;
    public String playerTwoName;
    private Game game;
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    private Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);

    public void goToGameScreen() throws IOException, IllegalInputException {
        try {
            Integer.parseInt(length.getText());
            Integer.parseInt(length.getText());
        } catch (NumberFormatException e) {
            informationAlert.setContentText("Not a valid input");
            informationAlert.showAndWait();
        }


        this.width_i = Integer.parseInt(length.getText());
        this.height_i = Integer.parseInt(height.getText());
        this.playerOneName = playerID1.getText();
        this.playerTwoName = playerID2.getText();
        if (height_i > 0 && height_i <= 1000 && width_i > 0 && width_i <= 1700) {
            this.game = new Game(playerOneName, playerTwoName,
                    height_i, width_i);
        } else {
            errorAlert.setContentText("Height must be between 0 and 1000, " +
                    "and width between 0 and 1700");
            errorAlert.showAndWait();
            throw new IllegalInputException("Height must be between 0 and 1000, " +
                    "and width between 0 and 1700");
        }

        GameScreen.setGame(game);
        SceneManager.changeScene("fxml/GameScreen.fxml");
    }
}