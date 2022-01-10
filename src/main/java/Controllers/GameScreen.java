package Controllers;


import ApplicationClasses.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameScreen {
    private static Game game;
    @FXML
    public Button btnBack; public TextField pl1ang; public TextField pl1vec;
    public TextField pl2ang; public TextField pl2vec; public ImageView bananaImg;
    public Label pl2AngLabel; public Label pl2VelLabel; public Label pl1AngLabel;
    public Label pl1VelLabel; public Label nameLabel1; public Label nameLabel2;
    public Button pl1start; public Button pl2start;
    public Label pl2NameLabel; public Label pl1NameLabel; public Label whoWantsLabel; public Button throwButton;
    public ImageView monkeyOne; public ImageView monkeyTwo;
    public Label score1; public Label score2;


    private Player player1; private Player player2;
    private World world;
    private int playerOneAngle; private int playerOneVelocity;
    private int playerTwoAngle; private int playerTwoVelocity;

    private List<Integer> list = new ArrayList<>();
    public boolean arr[][];
    private int[] bananaArr;
    Map<String, Integer> monkeyOneDimensions = new HashMap<String, Integer>();
    Map<String, Integer> monkeyTwoDimensions = new HashMap<String, Integer>();
    private int point1 = 0;
    private int point2 = 0;
    private Monkey monkey1;
    private Monkey monkey2;

    public boolean flag = false;


    public void goToMainScene() throws IOException {
        SceneManager.changeScene("fxml/MainScene.fxml");
    }


    public void hitBox() {
        if (!player1.getTurn()) {
            for (int i = monkeyOneDimensions.get("start_y"); i < monkeyOneDimensions.get("end_y"); i++) {
                for (int k = monkeyOneDimensions.get("start_x"); k < monkeyOneDimensions.get("end_x"); k++) {
                    if (i >= 0 && k >= 0 && i < 800 && k < 1300) {
                        arr[i][k] = true;
                    }
                }
            }
        } else {
            for (int i = monkeyTwoDimensions.get("start_y"); i < monkeyTwoDimensions.get("end_y"); i++) {
                for (int k = monkeyTwoDimensions.get("start_x"); k < monkeyTwoDimensions.get("end_x"); k++) {
                    if(i >= 0 && k >= 0 && i < 800 && k < 1300) {
                        arr[i][k] = true;
                    }
                }
            }
        }
    }

    public void doThrow(ActionEvent event) throws IOException {
        if (player1.getTurn()) {
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
        monkeyOneDimensions.put("start_x", (int) monkeyOne.getLayoutX());
        monkeyOneDimensions.put("end_x", (int) monkeyOne.getLayoutX() + (int) monkeyOne.getFitWidth());
        monkeyOneDimensions.put("start_y", (int) monkeyOne.getLayoutY());
        monkeyOneDimensions.put("end_y", (int) monkeyOne.getLayoutY() + (int) monkeyOne.getFitHeight());

        monkeyTwoDimensions.put("start_x", (int) monkeyTwo.getLayoutX());
        monkeyTwoDimensions.put("end_x", (int) monkeyTwo.getLayoutX() + (int) monkeyTwo.getFitWidth());
        monkeyTwoDimensions.put("start_y", (int) monkeyTwo.getLayoutY());
        monkeyTwoDimensions.put("end_y", (int) monkeyTwo.getLayoutY() + (int) monkeyTwo.getFitHeight());
    }

    public void makeBanana() {
        bananaArr = new int[4];
        bananaArr[0] = (int) bananaImg.getFitHeight();
        bananaArr[1] = (int) bananaImg.getFitWidth();
        bananaArr[2] = (int) bananaImg.getLayoutX();
        bananaArr[3] = (int) bananaImg.getLayoutY();
    }

    public void bananaHit(ImageView monkey) {
        flag = false;
        int indikator = 0;
        if(player1.getTurn()) {
            for (int j = 800 - (int) bananaImg.getY(); j < 800 - (int) bananaImg.getY() + bananaArr[0]; j++) {
                for (int k = (int) bananaImg.getX(); k < (int) bananaImg.getX() + bananaArr[1]; k++) {
                    if (j >= 0 && k >= 0 && j < 800 && k < 1300) {
                        if(arr[j][k]) {
                            bananaImg.setVisible(false);
                            monkey.setVisible(false);
                            indikator++;
                            flag = true;
                        }
                        if (indikator > 0) {
                            j = 800 - (int) bananaImg.getY() + bananaArr[0];
                            k = (int) bananaImg.getX() + bananaArr[1];
                        }
                    }
                }
            }
        } else {
            for (int j = 800 - (int) bananaImg.getY(); j < 800 - (int) bananaImg.getY() + bananaArr[0]; j++) {
                for (int k = (int) bananaImg.getX(); k < (int) bananaImg.getX() + bananaArr[1]; k++) {
                    if (j >= 0 && k >= 0 && j < monkey.getY() - 200 && k < monkey.getX() - 200) {
                        if(arr[j][k]) {
                            bananaImg.setVisible(false);
                            monkey.setVisible(false);
                            indikator++;
                            flag = true;
                        }
                        if (indikator > 0) {
                            j = 800 - (int) bananaImg.getY() + bananaArr[0];
                            k = (int) bananaImg.getX() + bananaArr[1];
                        }
                    }
                }
            }
        }
       // if(indikator > 0) {
          //  point();
        //}
    }

    public void bananaHit() {

    }

    public void restart() {
        if(player1.getTurn()) {
            bananaImg.setX(monkeyOne.getX());
            bananaImg.setY(100);
            bananaImg.setVisible(true);
            monkeyOne.setVisible(true);
        } else {
            bananaImg.setX(monkeyTwo.getX());
            bananaImg.setY(100);
            bananaImg.setVisible(true);
            monkeyTwo.setVisible(true);
        }
    }

    public void runThread() {
        makeMonkeys();
        hitBox();
        restart();
        list = new ArrayList<>();
        if (player1.getTurn()) {
            Banana banan = new Banana(playerOneVelocity, 9.82, playerOneAngle);
            list = makeCurve(banan);
            for (int i = 0; i < list.size(); i++) {
                bananaImg.setX(i);
                bananaImg.setY(list.get(i));
                bananaImg.isSmooth();
                makeBanana();
                simulateSlow();
                bananaHit(monkeyTwo);
            }
            if(flag){
                point();
            }
            switchVisibility();
            player1.setTurn(false);
            restart();
        } else {
            Banana banan = new Banana(playerTwoVelocity, 9.82, playerTwoAngle);
            list = makeCurve(banan);
            for (int i = 0; i < list.size(); i++) {
                bananaImg.setX(1200 - i);
                bananaImg.setY(list.get(list.size() - 1 - i));
                bananaImg.isSmooth();
                makeBanana();
                simulateSlow();
                bananaHit(monkeyOne);
            }
            if(flag){
                point();
            }
            switchVisibility();
            player1.setTurn(true);
        }
        simulateSlow();
        bananaImg.setVisible(false);
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
        initGameValues();
        arr = new boolean[world.getHeight()][world.getWidth()];
        nameLabel1.setText(player1.getName());
        nameLabel2.setText(player2.getName());
        player1.setTurn(true);
        player2.setTurn(false);
        makeBoardVisible();
    }

    public void pl2Start(ActionEvent actionEvent) {
        initGameValues();
        arr = new boolean[world.getHeight()][world.getWidth()];
        System.out.println();
        nameLabel1.setText(player1.getName());
        nameLabel2.setText(player2.getName());
        player1.setTurn(false);
        player2.setTurn(true);
        makeBoardVisible();
    }
    public void initGameValues(){
        this.player1 = game.getPlayer1();
        this.player2 = game.getPlayer2();
        this.world = game.getWorld();
        this.monkey1 = world.getMonkey1();
        this.monkey2 = world.getMonkey2();
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
        if (player1.getTurn()){
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

    public void point(){
        if (player1.getTurn()){
            this.point1++;
            System.out.println(point1);
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    score1.setText(String.valueOf(point1));
                }
            });
        } else {
            this.point2++;
            System.out.println(point2);
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    score2.setText(String.valueOf(point2));
                    System.out.println("dillerdaller");
                }
            });
        }
    }
}