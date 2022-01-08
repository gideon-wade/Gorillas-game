package Controllers;


import ApplicationClasses.Banana;
import ApplicationClasses.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameScreen {
    private static Game game;
    @FXML
    public Button btnBack;
    public TextField pl1ang;
    public TextField pl1vec;
    public TextField pl2ang;
    public TextField pl2vec;
    public ImageView banana;
    public Label pl2AngLabel;
    public Label pl2VelLabel;
    public Label pl1AngLabel;
    public Label pl1VelLabel;
    public Label nameLabel1;
    public Label nameLabel2;
    public Button pl1start;
    public Button pl2start;
    public Label pl2NameLabel;
    public Label pl1NameLabel;
    public Label whoWantsLabel;
    public ImageView pafiImg;
    public Button throwButton;
    public ImageView monkeyOne;
    public ImageView monkeyTwo;
    public Label score1;
    public Label score2;


    private int playerOneAngle;
    private int playerOneVelocity;
    private int playerTwoAngle;
    private int playerTwoVelocity;
    private List<Integer> list = new ArrayList<>();
    public boolean arr[][];
    public int[] monkeyOneArr, monkeyTwoArr;
    private int monkeyOne_start_x;
    private int monkeyOne_slut_x;
    private int monkeyOne_start_y;
    private int monkeyOne_slut_y;
    private int monkeyTwo_start_x;
    private int monkeyTwo_slut_x;
    private int monkeyTwo_start_y;
    private int monkeyTwo_slut_y;
    private int[] bananaArr;

    private int point1 = 0;
    private int point2 = 0;


    public void goToMainScene() throws IOException {
        SceneManager.changeScene("fxml/MainScene.fxml");
    }


    public void hitbox() {
        if (!game.player1.getTurn()) {
            for (int i = monkeyOne_start_y; i < monkeyOne_slut_y; i++) {
                for (int k = monkeyOne_start_x; k < monkeyOne_slut_x; k++) {
                    if (i >= 0 && k >= 0 && i < 800 && k < 1300) {
                        arr[i][k] = true;
                    }
                }
            }
        } else {
            for(int i = monkeyTwo_start_y; i < monkeyTwo_slut_y; i++) {
                for(int k = monkeyTwo_start_x; k < monkeyTwo_slut_x; k++) {
                    if(i >= 0 && k >= 0 && i < 800 && k < 1300) {
                        arr[i][k] = true;
                    }
                }
            }
        }
    }

    public void doThrow(ActionEvent event) throws IOException {
        if (game.player1.getTurn()) {
            this.playerOneAngle = Integer.parseInt(pl1ang.getText());
            this.playerOneVelocity = Integer.parseInt(pl1vec.getText());
        } else {
            this.playerTwoAngle = Integer.parseInt(pl2ang.getText());
            this.playerTwoVelocity = Integer.parseInt(pl2vec.getText());
        }
        Thread thread = new Thread(this::runThread);
        thread.start();
    }

    public void makeMonkeys() {

        monkeyOneArr = new int[4];
        monkeyTwoArr = new int[4];

        monkeyOneArr[0] = (int) monkeyOne.getFitHeight();
        monkeyOneArr[1] = (int) monkeyOne.getFitWidth();
        monkeyOneArr[2] = (int) monkeyOne.getLayoutX();
        monkeyOneArr[3] = (int) monkeyOne.getLayoutY();

        monkeyTwoArr[0] = (int) monkeyTwo.getFitHeight();
        monkeyTwoArr[1] = (int) monkeyTwo.getFitWidth();
        monkeyTwoArr[2] = (int) monkeyTwo.getLayoutX();
        monkeyTwoArr[3] = (int) monkeyTwo.getLayoutY();

        this.monkeyOne_start_x = monkeyOneArr[2];                   // 8
        this.monkeyOne_slut_x = monkeyOneArr[2] + monkeyOneArr[1];  // 126
        this.monkeyOne_start_y = monkeyOneArr[3];                   // 707
        this.monkeyOne_slut_y = monkeyOneArr[3] + monkeyOneArr[0];  // 799

        this.monkeyTwo_start_x = monkeyTwoArr[2];
        this.monkeyTwo_slut_x = monkeyTwoArr[2] + monkeyTwoArr[1];
        this.monkeyTwo_start_y = monkeyTwoArr[3];
        this.monkeyTwo_slut_y = monkeyTwoArr[3] + monkeyTwoArr[0];
    }

    public void makeBanana() {
        bananaArr = new int[4];
        bananaArr[0] = (int) banana.getFitHeight();
        bananaArr[1] = (int) banana.getFitWidth();
        bananaArr[2] = (int) banana.getLayoutX();
        bananaArr[3] = (int) banana.getLayoutY();
    }

    public void bananaHit(ImageView monkey) {
        int indikator = 0;
        if(game.player1.getTurn()) {
            for (int j = 800 - (int) banana.getY(); j < 800 - (int) banana.getY() + bananaArr[0]; j++) {
                for (int k = (int) banana.getX(); k < (int) banana.getX() + bananaArr[1]; k++) {
                    if (j >= 0 && k >= 0 && j < 800 && k < 1300) {
                        if(arr[j][k]) {
                            banana.setVisible(false);
                            monkey.setVisible(false);
                            indikator++;
                        }
                    }
                }
            }
        } else {
            for (int j = 800 - (int) banana.getY(); j < 800 - (int) banana.getY() + bananaArr[0]; j++) {
                for (int k = (int) banana.getX(); k < (int) banana.getX() + bananaArr[1]; k++) {
                    if (j >= 0 && k >= 0 && j < monkey.getY() - 200 && k < monkey.getX() - 200) {
                        if(arr[j][k]) {
                            banana.setVisible(false);
                            monkey.setVisible(false);
                            indikator++;
                        }
                    }
                }
            }
        }
        if(indikator > 0) {
            point();
        }
    }

    public void restart() {
        if(game.player1.getTurn()) {
            banana.setX(monkeyOne.getX());
            banana.setY(100);
            banana.setVisible(true);
            monkeyOne.setVisible(true);
        } else {
            banana.setX(monkeyTwo.getX());
            banana.setY(100);
            banana.setVisible(true);
            monkeyTwo.setVisible(true);
        }
    }

    public void runThread() {
        makeMonkeys();
        hitbox();
        restart();
        list = new ArrayList<>();
        if (game.player1.getTurn()) {

            Banana banan = new Banana(playerOneVelocity, 9.82, playerOneAngle);
            list = makeCurve(banan);
            for (int i = 0; i < list.size(); i++) {
                banana.setX(i);
                banana.setY(list.get(i));
                banana.isSmooth();
                makeBanana();
                simulateSlow();
                bananaHit(monkeyTwo);
            }
            switchVisibility();
            game.player1.setTurn(false);
            restart();
        } else {
            Banana banan = new Banana(playerTwoVelocity, 9.82, playerTwoAngle);
            list = makeCurve(banan);
            for (int i = 0; i < list.size(); i++) {
                banana.setX(1200 - i);
                banana.setY(list.get(list.size() - 1 - i));
                banana.isSmooth();
                makeBanana();
                simulateSlow();
                bananaHit(monkeyOne);
            }
            switchVisibility();
            game.player1.setTurn(true);
        }
        simulateSlow();
        banana.setVisible(false);
    }

    public void simulateSlow() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Integer> makeCurve(Banana banan) {
        int x = 1;
        while (banan.trajectory(x) > -1) {
            this.list.add(100 - banan.trajectory(x));
            x++;
        }
        return this.list;
    }

    public void switchVisibility() {
        pl1ang.setVisible(!pl1ang.isVisible());
        pl1vec.setVisible(!pl1vec.isVisible());
        pl1AngLabel.setVisible(!pl1AngLabel.isVisible());
        pl1VelLabel.setVisible(!pl1VelLabel.isVisible());

        pl2ang.setVisible(!pl2ang.isVisible());
        pl2vec.setVisible(!pl2vec.isVisible());
        pl2AngLabel.setVisible(!pl2AngLabel.isVisible());
        pl2VelLabel.setVisible(!pl2VelLabel.isVisible());
    }

    public static void setGame(Game game) {
        GameScreen.game = game;
    }

    public void pl1Start(ActionEvent actionEvent) {
        arr = new boolean[game.getHeight()][game.getLength()];
        nameLabel1.setText(game.player1.getName());
        nameLabel2.setText(game.player2.getName());
        game.player1.setTurn(true);
        game.player2.setTurn(false);
        makeBoardVisible();
    }

    public void pl2Start(ActionEvent actionEvent) {
        arr = new boolean[game.getHeight()][game.getLength()];
        nameLabel1.setText(game.player1.getName());
        nameLabel2.setText(game.player2.getName());
        game.player1.setTurn(false);
        game.player2.setTurn(true);
        makeBoardVisible();
    }

    public void makeBoardVisible() {
        whoWantsLabel.setVisible(false);
        pl1start.setVisible(false);
        pl2start.setVisible(false);
        throwButton.setVisible(true);
        pl1NameLabel.setVisible(true);
        pl2NameLabel.setVisible(true);
        nameLabel1.setVisible(true);
        nameLabel2.setVisible(true);
        if (game.player1.getTurn()){
            pl1AngLabel.setVisible(true);
            pl1VelLabel.setVisible(true);
            pl1ang.setVisible(true);
            pl1vec.setVisible(true);
        } else {
            pl2AngLabel.setVisible(true);
            pl2VelLabel.setVisible(true);
            pl2ang.setVisible(true);
            pl2vec.setVisible(true);
        }
    }

    public void point() {
        if (game.player1.getTurn()) {
            this.point1++;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    score1.setText(String.valueOf(point1));
                }
            });
        } else {
            this.point2++;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    score2.setText(String.valueOf(point2));
                }
            });
        }
    }
}
