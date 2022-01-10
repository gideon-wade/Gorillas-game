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
    public ImageView monkeyOneImg; public ImageView monkeyTwoImg;
    public Label score1; public Label score2;
    public ImageView poof;



    private Player player1; private Player player2;
    private World world;
    private int playerOneAngle; private int playerOneVelocity;
    private int playerTwoAngle; private int playerTwoVelocity;

    private List<Integer> list = new ArrayList<>();
    public boolean canHitGrid[][];
    private int[] bananaArr;
    Map<String, Integer> monkeyOneDimensions = new HashMap<String, Integer>();
    Map<String, Integer> monkeyTwoDimensions = new HashMap<String, Integer>();
    private int point1 = 0;
    private int point2 = 0;
    private Monkey monkey1;
    private Monkey monkey2;

    private boolean flag;


    public void goToMainScene() throws IOException {
        SceneManager.changeScene("fxml/MainScene.fxml");
    }

    public void hitBox() {
        if (!player1.getTurn()) {
            for (int i = monkeyOneDimensions.get("start_y"); i < monkeyOneDimensions.get("end_y"); i++) {
                for (int k = monkeyOneDimensions.get("start_x"); k < monkeyOneDimensions.get("end_x"); k++) {
                    if (i >= 0 && k >= 0 && i < 800 && k < 1300) {
                        canHitGrid[i][k] = true;
                    }
                }
            }
        } else {
            for (int i = monkeyTwoDimensions.get("start_y"); i < monkeyTwoDimensions.get("end_y"); i++) {
                for (int k = monkeyTwoDimensions.get("start_x"); k < monkeyTwoDimensions.get("end_x"); k++) {
                    if(i >= 0 && k >= 0 && i < 800 && k < 1300) {
                        canHitGrid[i][k] = true;
                    }
                }
            }
        }
    }

    public void doThrow(ActionEvent event) throws IOException {
        throwButton.setVisible(false);
        if (player1.getTurn()) {
            this.playerOneAngle = Integer.parseInt(pl1ang.getText());
            this.playerOneVelocity = Integer.parseInt(pl1vec.getText());
        } else {
            this.playerTwoAngle = Integer.parseInt(pl2ang.getText());
            this.playerTwoVelocity = Integer.parseInt(pl2vec.getText());
        }
        Thread thread = new Thread(this::runThread);
        thread.start();
        throwButton.setVisible(true);
    }

    public void makeMonkeys() {
        monkeyOneDimensions.put("start_x", (int) monkeyOneImg.getLayoutX());
        monkeyOneDimensions.put("end_x", (int) monkeyOneImg.getLayoutX() + (int) monkeyOneImg.getFitWidth());
        monkeyOneDimensions.put("start_y", (int) monkeyOneImg.getLayoutY());
        monkeyOneDimensions.put("end_y", (int) monkeyOneImg.getLayoutY() + (int) monkeyOneImg.getFitHeight());

        monkeyTwoDimensions.put("start_x", (int) monkeyTwoImg.getLayoutX());
        monkeyTwoDimensions.put("end_x", (int) monkeyTwoImg.getLayoutX() + (int) monkeyTwoImg.getFitWidth());
        monkeyTwoDimensions.put("start_y", (int) monkeyTwoImg.getLayoutY());
        monkeyTwoDimensions.put("end_y", (int) monkeyTwoImg.getLayoutY() + (int) monkeyTwoImg.getFitHeight());
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
        for (int j = 800 - (int) bananaImg.getY(); j < 800 - (int) bananaImg.getY() + bananaArr[0]; j++) {
            for (int k = (int) bananaImg.getX(); k < (int) bananaImg.getX() + bananaArr[1]; k++) {
                if (player1.getTurn() && j >= 0 && k >= 200 && j < world.getHeight() && k < world.getWidth()) {
                    if(canHitGrid[j][k] || j == 800 && (canHitGrid[j][k - world.getWidth()/50]) ||
                            canHitGrid[j][world.getWidth()/50 + k])
                            {
                        bananaImg.setVisible(false);
                        poof.setLayoutX(monkey2.getStart_x());
                        poof.setLayoutY(world.getHeight() - poof.getFitHeight());
                        monkey.setVisible(false);
                        poof.setVisible(true);
                        flag = true;
                    }
                } else if (!player1.getTurn() && j >= 0 && k >= 0 && j < world.getHeight() && k < monkeyOneDimensions.get("end_x")) {
                    if(canHitGrid[j][k] || j == 800 && (canHitGrid[j][k - world.getWidth()/50]) ||
                            canHitGrid[j][world.getWidth()/50 + k]) {
                        bananaImg.setVisible(false);
                        poof.setLayoutX(monkey1.getStart_x());
                        poof.setLayoutY(world.getHeight() - poof.getFitHeight());
                        monkey.setVisible(false);
                        poof.setVisible(true);
                        flag = true;
                    }
                }
            }
        }
    }

    public void restart() {
        if(player1.getTurn()) {
            bananaImg.setX(monkeyOneImg.getX());
        } else {
            bananaImg.setX(monkeyTwoImg.getX());
        }
        monkeyOneImg.setVisible(true);
        monkeyTwoImg.setVisible(true);
        poof.setVisible(false);
        bananaImg.setVisible(true);
    }

    public void runThread() {
        makeMonkeys();
        hitBox();
        restart();
        list = new ArrayList<>();
        if (player1.getTurn()) {
            Banana banana = new Banana(playerOneVelocity, 9.82, playerOneAngle);
            list = makeCurve(banana);
            for (int i = 0; i < list.size(); i++) {
                bananaImg.setX(monkey1.getStart_x() + i);
                bananaImg.setY(list.get(i));
                bananaImg.isSmooth();
                makeBanana();
                simulateSlow();
                bananaHit(monkeyTwoImg);
            }
            player1.setTurn(false);
        } else {
            Banana banana = new Banana(playerTwoVelocity, 9.82, playerTwoAngle);
            list = makeCurve(banana);
            for (int i = 0; i < list.size(); i++) {
                bananaImg.setX(world.getWidth() - 100 - i);
                bananaImg.setY(list.get(list.size() - 1 - i));
                bananaImg.isSmooth();
                makeBanana();
                simulateSlow();
                bananaHit(monkeyOneImg);
            }
            player1.setTurn(true);
        }
        if(flag) point();
        switchVisibility();
        restart();
        simulateSlow();
        bananaImg.setVisible(false);
    }

    public void simulateSlow() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Integer> makeCurve(Banana banana) {
        int x = 1;
        while (banana.trajectory(x) > -1) {
            this.list.add(100 - banana.trajectory(x));
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
        player1.setTurn(true);
        player2.setTurn(false);
        makeBoardVisible();
    }

    public void pl2Start(ActionEvent actionEvent) {
        initGameValues();
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
        this.canHitGrid = world.getCantHitGrid();
        nameLabel1.setText(player1.getName());
        nameLabel2.setText(player2.getName());
        monkeyOneImg.setLayoutX(0);
        monkeyOneImg.setLayoutY(world.getHeight() - monkeyTwoImg.getFitHeight());
        monkeyTwoImg.setLayoutX(world.getWidth() - monkeyTwoImg.getFitWidth());
        monkeyTwoImg.setLayoutY(world.getHeight() - monkeyTwoImg.getFitHeight());
        monkeyOneImg.setVisible(true);
        monkeyTwoImg.setVisible(true);
        monkeyOneImg.isSmooth();
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
        if (!player1.getTurn()){
            this.point1++;
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    score1.setText(String.valueOf(point1));
                }
            });
        } else {
            this.point2++;
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    score2.setText(String.valueOf(point2));
                }
            });
        }
    }
}