import  Controllers.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene container = new Scene(new Pane(),1300,800);
        SceneManager.setMainScene(container);
        primaryStage.setScene(container);
        SceneManager.changeScene("fxml/MainScene.fxml");
        primaryStage.setTitle("Gorillas");
        primaryStage.show();
    }
}
