package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SceneManager {
    private static Scene mainScene;

    /**
     * Used to chage what is display on the primary stage
     * @param fxml the file name of the FXML file (including .fxml)
     * @throws IOException if file is not found or cannot be read
     */

    public static void changeScene(String fxml) throws IOException {
        mainScene.setRoot(FXMLLoader.load(SceneManager.class.getResource("/" + fxml)));
        /*Image image = new Image("banana.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(image);*/
    }

    public static void changeSize(int height, int width) {
        mainScene.getWindow().setHeight(height);
        mainScene.getWindow().setWidth(width);
    }

    public static void setMainScene(Scene mainScene) {
        SceneManager.mainScene = mainScene;
    }
}
