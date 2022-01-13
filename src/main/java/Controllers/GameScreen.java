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
import java.util.*;

public class GameScreen {
    private static Game game;
    @FXML
    public Button btnBack; public TextField pl1ang; public TextField pl1vec;
    public TextField pl2ang; public TextField pl2vec; public ImageView bananaImg;
    public Label pl2AngLabel; public Label pl2VelLabel; public Label pl1AngLabel;
    public Label pl1VelLabel; public Label nameLabel1; public Label nameLabel2;
    public Button pl1start; public Button pl2start;
    public Label pl2NameLabel; public Label pl1NameLabel; public Label whoWantsLabel;
    public Button throwButton;
    public ImageView monkeyOneImg; public ImageView monkeyTwoImg;
    public Label score1; public Label score2;
    public ImageView poof;
    public ImageView explosion;
    public ImageView barLeft; public ImageView barLower; public ImageView barRight;


    private Player player1; private Player player2;
    private World world;
    private int playerOneAngle; private int playerOneVelocity;
    private int playerTwoAngle; private int playerTwoVelocity;

    private List<Integer> list = new ArrayList<>();
    public boolean canHitGrid[][];
    private int[] bananaArr;
    private int point1 = 0;
    private int point2 = 0;
    private Monkey monkey1;
    private Monkey monkey2;
    private boolean flag;


    public void goToMainScene() throws IOException {
        SceneManager.changeScene("fxml/MainScene.fxml");
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

    public void initGameValues() {
        this.player1 = game.getPlayer1();
        this.player2 = game.getPlayer2();
        this.world = game.getWorld();
        this.monkey1 = world.getMonkey1();
        this.monkey2 = world.getMonkey2();
        this.canHitGrid = world.getCantHitGrid();
        nameLabel1.setText(player1.getName());
        nameLabel2.setText(player2.getName());
        monkeyOneImg.setLayoutX(monkey1.getStart_x());
        monkeyOneImg.setLayoutY(monkey1.getStart_y());
        monkeyTwoImg.setLayoutX(monkey2.getStart_x());
        monkeyTwoImg.setLayoutY(monkey2.getStart_y());
        barLeft.setLayoutX(monkey1.getStart_x());
        barLeft.setLayoutY(0);
        barLower.setLayoutX(monkey1.getStart_x());
        barLower.setLayoutY(monkey1.getEnd_y());
        barLower.setFitWidth(world.getWidth());
        barRight.setLayoutX(monkey2.getEnd_x());
        barRight.setLayoutY(0);
        monkeyOneImg.setVisible(true);
        monkeyTwoImg.setVisible(true);
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

    public void doThrow(ActionEvent event) throws IOException {
        bananaImg.setVisible(true);
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
    }

    public void runThread() {
        hitBox();
        list = new ArrayList<>();
        if (player1.getTurn()) {
            Banana banana = new Banana(playerOneVelocity, 9.82, playerOneAngle);
            list = makeCurve(banana);
            for (int i = 0; i < list.size(); i++) {
                bananaImg.setLayoutY(800 - monkeyOneImg.getFitHeight() - list.get(i));
                bananaImg.setLayoutX(monkey1.getEnd_x() + i);
                explosion.setLayoutX(bananaImg.getLayoutX() - (explosion.getFitWidth()/2));
                explosion.setLayoutY(bananaImg.getLayoutY() - (explosion.getFitHeight()/2));
                bananaImg.isSmooth();
                makeBanana();
                simulateSlow(-1);
                bananaHit(monkeyTwoImg);
            }
            simulateSlow(200);
            player1.setTurn(false);
        } else {
            Banana banana = new Banana(playerTwoVelocity, 9.82, playerTwoAngle);
            list = makeCurve(banana);
            for (int i = 0; i < list.size(); i++) {
                bananaImg.setLayoutY(800 - monkeyTwoImg.getFitHeight() - (list.get(i)));
                bananaImg.setLayoutX(monkey2.getStart_x() - i);
                explosion.setLayoutX(bananaImg.getLayoutX() - (explosion.getFitWidth()/2));
                explosion.setLayoutY(bananaImg.getLayoutY() - (explosion.getFitHeight()/2));
                bananaImg.isSmooth();
                makeBanana();
                simulateSlow(-1);
                bananaHit(monkeyOneImg);
            }
            simulateSlow(200);
            player1.setTurn(true);
        }
        if(flag) point();
        switchVisibility();
        restart();
        simulateSlow(0);
        bananaImg.setVisible(false);
    }

    public void hitBox() {
        if (!player1.getTurn()) {
            for (int i = monkey1.getStart_y(); i < monkey1.getEnd_y(); i++) {
                for (int k = monkey1.getStart_x(); k < monkey1.getEnd_x(); k++) {
                    if (i >= 0 && k >= 0 && i < world.getHeight() && k < world.getWidth()) {
                        canHitGrid[i][k] = true;
                    }
                }
            }
        } else {
            for (int i = monkey2.getStart_y(); i < monkey2.getEnd_y(); i++) {
                for (int k = monkey2.getStart_x(); k < monkey2.getEnd_x(); k++) {
                    if(i >= 0 && k >= 0 && i < world.getHeight() && k < world.getWidth()) {
                        canHitGrid[i][k] = true;
                    }
                }
            }
        }
    }

    public void restart() {
        if(player1.getTurn()) {
            bananaImg.setLayoutX(monkey1.getEnd_x());
        } else {
            bananaImg.setLayoutX(world.getWidth() - monkeyTwoImg.getFitWidth());
        }
        monkeyOneImg.setVisible(true);
        monkeyTwoImg.setVisible(true);
        poof.setVisible(false);
        bananaImg.setVisible(true);
        explosion.setVisible(false);
        throwButton.setVisible(true);
    }

    public List<Integer> makeCurve(Banana banana) {
        int x = 1;
        while (banana.trajectory(x) > - monkeyOneImg.getFitHeight()
                && (x < monkey2.getEnd_x() - monkey1.getStart_x())) {
            this.list.add(banana.trajectory(x));
            x++;
        }
        return this.list;
    }

    public void makeBanana() {
        bananaArr = new int[4];
        bananaArr[0] = (int) bananaImg.getFitHeight();
        bananaArr[1] = (int) bananaImg.getFitWidth();
        bananaArr[2] = (int) bananaImg.getLayoutX();
        bananaArr[3] = (int) bananaImg.getLayoutY();
    }

    public void simulateSlow(int x) {
        try {
            bananaImg.getX();
            Thread.sleep(x + 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void bananaHit(ImageView monkey) {
        explosion.setVisible(false);
        flag = false;
        for (int j = (int) bananaImg.getLayoutY(); j < (int) bananaImg.getLayoutY() + bananaArr[0]; j++) {
            for (int k = (int) bananaImg.getLayoutX(); k < (int) bananaImg.getLayoutX() + bananaArr[1]; k++) {
                if (player1.getTurn() && j >= 0 && k >= monkey1.getEnd_x() && j <
                        800 && k < 1300) {
                    if(canHitGrid[j][k]) {
                        bananaImg.setVisible(false);
                        explosion.setVisible(true);
                        if (bananaImg.getLayoutX() > monkey2.getStart_x() - 50 && bananaExplosion(j, k)) {
                            poof.setLayoutX(monkey2.getStart_x());
                            poof.setLayoutY(world.getHeight() - poof.getFitHeight());
                            monkey.setVisible(false);
                            poof.setVisible(true);
                            flag = true;
                        }
                    } else{
                        noHit();
                    }
                } else if (!player1.getTurn() && j >= 0 && k >= 0 && j <
                        800 && k < (monkey2.getStart_x())) {
                    if(canHitGrid[j][k]) {
                        bananaImg.setVisible(false);
                        explosion.setVisible(true);
                        if (bananaImg.getLayoutX() < monkey1.getEnd_x() + 50 && bananaExplosion(j, k)){
                            poof.setLayoutX(monkey1.getStart_x());
                            poof.setLayoutY(world.getHeight() - poof.getFitHeight());
                            monkey.setVisible(false);
                            poof.setVisible(true);
                            flag = true;
                        }
                    } else{
                        noHit();
                    }
                }
            }
        }
    }

    public void noHit(){
        if (bananaImg.getLayoutY() >= world.getHeight()) {
            explosion.setVisible(true);
        }
    }

    public boolean bananaExplosion(int y, int x) {
        if((x + (1300 / 10)) < 1300 && (x - (1300 / 10)) > 0) {
            return y > 800 - 3 && ((canHitGrid[y][(x - (1300 / 10))]) ||
                    (canHitGrid[y][(x + (1300 / 10))]));
        }
        return false;
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