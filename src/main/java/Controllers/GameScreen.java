package Controllers;


import ApplicationClasses.*;

import Exceptions.IllegalInputException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.*;


public class GameScreen {
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
    public ImageView poof1; public ImageView poof2;
    public ImageView explosion;
    public ImageView barLeft; public ImageView barLower;
    public ImageView barRight;

    public ImageView clouds1; public ImageView clouds2;
    public ImageView clouds3; public ImageView clouds4;

    private static Game game;
    private Player player1; private Player player2;
    private World world;
    private boolean canHitGrid[][];
    private int playerOneAngle; private int playerOneVelocity;
    private int playerTwoAngle; private int playerTwoVelocity;
    private List<Integer> curve = new ArrayList<>();
    private int point1 = 0;
    private int point2 = 0;
    private Monkey monkey1;
    private Monkey monkey2;
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    private Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
    private boolean flag;
    public boolean stop = false;
    public static final int maxHeight = 1000;
    public static final int maxWidth = 1700;



    public void goToMainScene() throws IOException {
        SceneManager.changeScene("fxml/MainScene.fxml");
    }

    public static void setGame(Game game) {
        GameScreen.game = game;
    }


    /* if this method is called by pressing a button in the gamescreen, player 1 starts */
    public void pl1Start(ActionEvent actionEvent) {
        initGameValues();
        player1.setTurn(true);
        player2.setTurn(false);
        makeBoardVisible();


    }
    /* if this method is called by pressing a button in the gamescreen, player 2 starts */
    public void pl2Start(ActionEvent actionEvent) {
        initGameValues();
        player1.setTurn(false);
        player2.setTurn(true);
        makeBoardVisible();

    }

    /* initGameValues() initializes game values eg. assigning the variables with their desired values  */
    public void initGameValues(){
        this.player1 = game.getPlayer1();
        this.player2 = game.getPlayer2();
        this.world = game.getWorld();
        this.monkey1 = world.getMonkey1();
        this.monkey2 = world.getMonkey2();
        poof1.setLayoutX(monkey1.getStart_x() - 50);
        poof1.setLayoutY(monkey1.getStart_y() - 50);
        poof2.setLayoutX(monkey2.getStart_x() - 50);
        poof2.setLayoutY(monkey2.getStart_y() - 50);
        if (player1.getName().isEmpty()){
            nameLabel1.setText("Player 1");
        } else{ nameLabel1.setText(player1.getName());}
        if (player2.getName().isEmpty()){
            nameLabel2.setText("Player 2");
        } else{ nameLabel2.setText(player2.getName());}
        monkeyOneImg.setLayoutX(monkey1.getStart_x());
        monkeyOneImg.setLayoutY(monkey1.getStart_y());
        monkeyTwoImg.setLayoutX(monkey2.getStart_x());
        monkeyTwoImg.setLayoutY(monkey2.getStart_y());
        barLeft.setLayoutX(monkey1.getStart_x());
        barLeft.setLayoutY(0);
        barLeft.setFitHeight(maxHeight);
        barLower.setLayoutX(monkey1.getStart_x());
        barLower.setLayoutY(monkey1.getEnd_y());
        barLower.setFitWidth(world.getWidth());
        barRight.setLayoutX(monkey2.getEnd_x());
        barRight.setLayoutY(0);
        barRight.setFitHeight(maxHeight);
        monkeyOneImg.setVisible(true);
        monkeyTwoImg.setVisible(true);
        if(world.getHeight() <= 900) clouds1.setVisible(true);
        if(world.getHeight() <= 800) clouds2.setVisible(true);
        if(world.getHeight() <= 700) clouds3.setVisible(true);
        if(world.getHeight() == 600) clouds4.setVisible(true);
    }
    /* makes all elements of the gamescreen visible. The if/else statement is checking which player goes first,
       and displaying their respectable elements */
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
    /* this actionevent executes when the throw button is pressed
     * firstly the randomAdder() generates a random number
     * secondly direction is executed, which displays and arrow and windspeed on the gamescreen.
     * then the banana is set to visible and the throwbutton is set to invisible.
     * The angle and velocity is then assigned to the variables depending on which turn it is.
     *  */
    public void doThrow(ActionEvent event) throws IOException, IllegalInputException {
        throwButton.setVisible(false);
        try {
            if(player1.getTurn()){
                Integer.parseInt(pl1vec.getText());
                Integer.parseInt(pl1ang.getText());
            } else {
                Integer.parseInt(pl2vec.getText());
                Integer.parseInt(pl2ang.getText());
            }
        } catch (NumberFormatException e) {
            informationAlert.setContentText("Not a valid input");
            informationAlert.showAndWait();
            throwButton.setVisible(true);
        }
        if (player1.getTurn()) { // these if statements throws an exception if the textfields are empty
            if(pl1ang.getText().isEmpty() || pl1vec.getText().isEmpty()){
                throwButton.setVisible(true);
                errorAlert.setContentText("Actionfelterne må ikke være tomme");
                errorAlert.showAndWait();
                throw new IllegalInputException("Actionfelterne må ikke være tomme");
            }
            if(Integer.parseInt(pl1vec.getText()) > 0 &&
                    90 > Integer.parseInt(pl1ang.getText()) && Integer.parseInt(pl1ang.getText()) > 0) {
                this.playerOneAngle = Integer.parseInt(pl1ang.getText());
                this.playerOneVelocity = Integer.parseInt(pl1vec.getText());
            } else { // throws an exception if the textfields has an illegal input
                makeBoardVisible();
                bananaImg.setVisible(false);
                errorAlert.setContentText("Farten skal væres større end 0 og vinklen skal være mellem 0 og 90");
                errorAlert.showAndWait();
                throw new IllegalInputException("Farten skal væres større end 0 og vinklen skal være mellem 0 og 90");
            }
        } else { // does the same as line 386, but for the other player
            if (pl2ang.getText().isEmpty() || pl2vec.getText().isEmpty()){
                throwButton.setVisible(true);
                errorAlert.setContentText("Actionfelterne må ikke være tomme");
                errorAlert.showAndWait();
                throw new IllegalInputException("Actionfelterne må ikke være tomme");
            }
            if(Integer.parseInt(pl2vec.getText()) > 0 &&
                    90 > Integer.parseInt(pl2ang.getText()) && Integer.parseInt(pl2ang.getText()) > 0) {
                this.playerTwoAngle = Integer.parseInt(pl2ang.getText());
                this.playerTwoVelocity = Integer.parseInt(pl2vec.getText());
            } else { // does the same as 397, but for the other player
                makeBoardVisible();
                bananaImg.setVisible(false);
                errorAlert.setContentText("Farten skal væres større end 0 og vinklen skal være mellem 0 og 90");
                errorAlert.showAndWait();
                throw new IllegalInputException("Farten skal væres større end 0 og vinklen skal være mellem 0 og 90");
            }
        } // resets the textfields
        pl1ang.setText("");
        pl2ang.setText("");
        pl1vec.setText("");
        pl2vec.setText("");
        bananaImg.setVisible(true);
        Thread thread = new Thread(this::runThread);
        thread.start();
    }
    public void runThread() {
        world.hitBox(player1);
        world.hitBox(player1);
        curve = new ArrayList<>();
        flag = false;
        if (player1.getTurn()) {
            this.canHitGrid = world.getCanHitGrid();
            Banana banana = new Banana(playerOneVelocity, 9.82, playerOneAngle);
            curve = makeCurve(banana);
            for (int i = 0; i < curve.size(); i++) {
                bananaImg.setLayoutY(1000 - monkeyOneImg.getFitHeight() - curve.get(i));
                bananaImg.setLayoutX(monkey1.getEnd_x() + i);
                explosion.setLayoutX(bananaImg.getLayoutX() - (explosion.getFitWidth()/2));
                explosion.setLayoutY(bananaImg.getLayoutY() - (explosion.getFitHeight()/2));
                bananaImg.isSmooth();
                simulateSlow(-1);
                bananaHit(monkeyTwoImg);
            }
            simulateSlow(200);
            player1.setTurn(false);

        } else {
            this.canHitGrid = world.getCanHitGrid();
            Banana banana = new Banana(playerTwoVelocity, 9.82, playerTwoAngle);
            curve = makeCurve(banana);
            for (int i = 0; i < curve.size(); i++) { // this for-loop changes the location of the banana image
                bananaImg.setLayoutY(1000 - monkeyTwoImg.getFitHeight() - (curve.get(i)));
                bananaImg.setLayoutX(monkey2.getStart_x() - i);
                explosion.setLayoutX(bananaImg.getLayoutX() - (explosion.getFitWidth()/2));
                explosion.setLayoutY(bananaImg.getLayoutY() - (explosion.getFitHeight()/2));
                bananaImg.isSmooth();
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
    /* makecurve generates the values of the curve, so they can be used to update the location of the banana
     * line 390 makes the wind go the correct direction for both players and line 391 makes the wind
     * fluctuate (see RandomAdder() line 381) */
    public List<Integer> makeCurve(Banana banana) {
        int x = 0;
        while (banana.trajectory(x) > - monkeyOneImg.getFitHeight()
                && (x < (monkey2.getStart_x() - monkey1.getStart_x()) + bananaImg.getFitWidth())) {
            this.curve.add(banana.trajectory(x));
            x++;
        }
        return this.curve;
    }
    /* simulateSlow() is a method that can be called for slowing down the code */
    public void simulateSlow(int x) {
        try {
            Thread.sleep(x + 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /* the bananaHit method checks if the banana hit a monkey or building/Tree/Food
     * this is done with boolean grids. If the banana hits, the monkey will disappear
     * for a couple of milliseconds and an explosion and a poof will appear instead.
     * This counts for both players, depending on the if/else staments. If none of the
     * statements are true noHit() wil get executed. */
    public void bananaHit(ImageView monkey) {
        explosion.setVisible(false);
        // makes the thread stop
        //The for-for-loop iterates through every pixel in that our bananaImg contains
        for (int j = (int) bananaImg.getLayoutY(); j < (int) bananaImg.getLayoutY() + bananaImg.getFitHeight(); j++) {
            for (int k = (int) bananaImg.getLayoutX(); k < (int) bananaImg.getLayoutX() + bananaImg.getFitWidth(); k++) {
                if (player1.getTurn() && j >= 0 && k >= monkey1.getEnd_x() && j <
                        1000 && k < 1700) {
                    if(canHitGrid[j][k]) {
                        bananaImg.setVisible(false);
                        explosion.setVisible(true);
                        if (bananaImg.getLayoutX() + bananaImg.getFitWidth() >= monkey2.getStart_x() &&
                                bananaImg.getLayoutX() + bananaImg.getFitWidth()<= monkey2.getEnd_x() && bananaExplosion(j, k)) {
                            monkey.setVisible(false);
                            poof2.setVisible(true);
                            flag = true;
                        }
                    } else{
                        noHit();
                    }
                } else if (!player1.getTurn() && j >= 0 && k >= 0 && j <
                        1000 && k < (monkey2.getStart_x())) {
                    if(canHitGrid[j][k]) {
                        bananaImg.setVisible(false);
                        explosion.setVisible(true);
                        if (bananaImg.getLayoutX() + bananaImg.getFitWidth() >= monkey1.getStart_x() &&
                                bananaImg.getLayoutX() + bananaImg.getFitWidth()<= monkey1.getEnd_x() && bananaExplosion(j, k)){
                            monkey.setVisible(false);
                            poof1.setVisible(true);
                            flag = true;
                        }
                    } else {
                        noHit();
                    }
                }
            }
        }
    }

    public void noHit(){
        if (bananaImg.getLayoutY() >= 1000 - 1
                || bananaImg.getLayoutX() < monkey1.getStart_x()
                || bananaImg.getLayoutX() > monkey2.getEnd_x()) {
            explosion.setVisible(true);
        }
    }

    public boolean bananaExplosion(int y, int x) {
        if((x + (maxWidth / 50)) < maxWidth && (x - (maxWidth / 50)) > 0) {
            return y > 1000 - 3 && ((canHitGrid[y][(x - (maxWidth / 50))]) ||
                    (canHitGrid[y][(x + (maxWidth / 50))]));
        }
        return false;
    }
    /* the point() method adds a point to a players score and updates the label on the gamescreen
    if the method is run */
    public void point(){
        if (!player1.getTurn()){ // checks whose turn it is.
            this.point1++; // gives a point if the if-statement is true
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    score1.setText(String.valueOf(point1));
                }
                /* updates the score1-label without the need of an actionevent  */
            });
        } else { // this is exactly the same
            this.point2++;
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    score2.setText(String.valueOf(point2));
                }
            });
        }
    }
    /* switchVisibility() switches the visibilty of the action-boxes of each player */
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
    /* restart() restarts the position of the banana, as well as resetting the
     * visibility of the elements to the original */
    public void restart() {
        if(player1.getTurn()) {
            bananaImg.setLayoutX(monkey1.getEnd_x());
        } else {
            bananaImg.setLayoutX(world.getWidth() - monkeyTwoImg.getFitWidth());
        }
        monkeyOneImg.setVisible(true);
        monkeyTwoImg.setVisible(true);
        poof1.setVisible(false);
        poof2.setVisible(false);
        bananaImg.setVisible(true);
        explosion.setVisible(false);
        throwButton.setVisible(true);
    }
}