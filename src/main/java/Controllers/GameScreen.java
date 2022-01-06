package Controllers;


import ApplicationClasses.Banana;
import ApplicationClasses.Game;
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
    private static Game gamer;
    @FXML
    public Button btnBack;
    public TextField pl1ang;
    public TextField pl1vec;
    public TextField pl2ang;
    public TextField pl2vec;
    public ImageView banana;
    public Button Button;
    public Label pl2anglabel;
    public Label pl2vellabel;
    public Label pl1anglabel;
    public Label pl1vellabel;
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


    private int playerOneAngle;
    private int playerOneVelocity;
    private int playerTwoAngle;
    private int playerTwoVelocity;
    private List<Integer> list = new ArrayList<>();
    public boolean arr[][];
    public int[] monkeyOneArr, monkeyTwoArr;
    int start_x;
    int slut_x;
    int start_y;
    int slut_y;

    public void goToMainScene() throws IOException {
        SceneManager.changeScene("fxml/MainScene.fxml");
    }

   // Game gamer = new Game("Søren","Gucci",800,1300);

   public void grid(int rows, int columns) {
       this.arr = new boolean[rows][columns];
   }

   public void hitbox() {
        for(int i = start_x; i < slut_x; i++) {
            for(int k = start_y; k < slut_y; k++) {
                if(i >= 0 && k >= 0) {
                    arr[i][k] = true;
                }
            }
        }
   }

    public void doThrow(ActionEvent event) throws IOException {
        if (gamer.player1.getTurn()) {
            this.playerOneAngle = Integer.parseInt(pl1ang.getText());
            this.playerOneVelocity = Integer.parseInt(pl1vec.getText());
            System.out.println(playerOneAngle);
        } else {
            this.playerTwoAngle = Integer.parseInt(pl2ang.getText());
            this.playerTwoVelocity = Integer.parseInt(pl2vec.getText());
            System.out.println(playerTwoAngle);
        }

        Thread thread = new Thread(this::runThread);
        thread.start();
    }

    public void makeMonkeys() {
        monkeyOneArr = new int[4];
        monkeyTwoArr = new int[4];

        monkeyOneArr[0] = (int) monkeyOne.getFitHeight();
        monkeyOneArr[1] = (int) monkeyOne.getFitWidth();
        monkeyOneArr[2] = (int) monkeyOne.getX();
        monkeyOneArr[3] = (int) monkeyOne.getY();

        monkeyTwoArr[0] = (int) monkeyTwo.getFitHeight();
        monkeyTwoArr[1] = (int) monkeyTwo.getFitWidth();
        monkeyTwoArr[2] = (int) monkeyTwo.getX();
        monkeyTwoArr[3] = (int) monkeyTwo.getY();

        this.start_x = monkeyOneArr[2] - (monkeyOneArr[1]/2);
        this.slut_x = monkeyOneArr[2] + (monkeyOneArr[1]/2);
        this.start_y = monkeyOneArr[3] - (monkeyOneArr[0]/2);
        this.slut_y = monkeyOneArr[3] + (monkeyOneArr[0]/2);
    }

    public void runThread() {
        makeMonkeys();
        hitbox();
        list = new ArrayList<>();
        if (gamer.player1.getTurn()) {
            banana.setX(1);
            banana.setY(100);
            banana.setVisible(true);

            Banana banan = new Banana(playerOneVelocity, 9.82, playerOneAngle);
            list = makeCurve(banan);

            for (int i = 0; i < list.size(); i++) {

                    banana.setX(i);

                banana.setY(list.get(i));
                banana.isSmooth();
                simulateSlow();
            }

            switchVisibility();

            gamer.player1.setTurn(false);

        } else {
            System.out.println("Passed else");
            banana.setX(1200);
            banana.setY(100);
            banana.setVisible(true);
            System.out.println("pl2vel: " + playerTwoVelocity);
            System.out.println("pl2ang: " + playerTwoAngle);
            Banana banan = new Banana(playerTwoVelocity, 9.82, playerTwoAngle);

            list = makeCurve(banan);



            for (int i = 0; i < list.size(); i++) {


                    banana.setX(1200 - i);

                banana.setX(1200 - i);
                banana.setY(list.get(list.size() - 1 - i));
                banana.isSmooth();
                simulateSlow();
            }

            switchVisibility();

            gamer.player1.setTurn(true);
        }
        simulateSlow();
        banana.setVisible(false);
    }

    public void simulateSlow() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Integer> makeCurve(Banana banan){
        int x = 1;
        while (banan.trajectory(x) > -1) {
            this.list.add(100 - banan.trajectory(x));
            banana.isSmooth();
            x++;
        }



        return this.list;
    }

    public void switchVisibility() {
        pl1ang.setVisible(!pl1ang.isVisible());
        pl1vec.setVisible(!pl1vec.isVisible());
        pl1anglabel.setVisible(!pl1anglabel.isVisible());
        pl1vellabel.setVisible(!pl1vellabel.isVisible());

        pl2ang.setVisible(!pl2ang.isVisible());
        pl2vec.setVisible(!pl2vec.isVisible());
        pl2anglabel.setVisible(!pl2anglabel.isVisible());
        pl2vellabel.setVisible(!pl2vellabel.isVisible());
    }
    /*
    public void setName(String playerOneName, String playerTwoName) {
        nameLabel1.setText(playerOneName);
        nameLabel2.setText(playerTwoName);
    }*/

    public void showCurve(List list){ // Not needed for final project, but helps to get an insight
        String s = "";

        for (int i = 0; i < list.size(); i++) {
            s += " " + list.get(i);
        }
        System.out.println(s);
    }
    public static void setGame(Game game){
        gamer = game;
    }

    public void pl1Start(ActionEvent actionEvent) {
        grid(gamer.getLength(), gamer.getHeight());
        nameLabel1.setText(gamer.player1.getName());
        nameLabel2.setText(gamer.player2.getName());
        gamer.player1.setTurn(true);
        gamer.player2.setTurn(false);
        makeBoardVisible();
    }

    public void pl2Start(ActionEvent actionEvent) {
        grid(gamer.getLength(), gamer.getHeight());
        nameLabel1.setText(gamer.player1.getName());
        nameLabel2.setText(gamer.player2.getName());
        gamer.player1.setTurn(false);
        gamer.player2.setTurn(true);

        makeBoardVisible();
    }

    public void makeBoardVisible() {
        whoWantsLabel.setVisible(false);
        pl1start.setVisible(false);
        pl2start.setVisible(false);
        pafiImg.setVisible(true);
        throwButton.setVisible(true);
        if (gamer.player1.getTurn()){
            pl1NameLabel.setVisible(true);
            pl1anglabel.setVisible(true);
            pl1vellabel.setVisible(true);
            nameLabel1.setVisible(true);
            pl1ang.setVisible(true);
            pl1vec.setVisible(true);
        } else {
            pl2NameLabel.setVisible(true);
            pl2anglabel.setVisible(true);
            pl2vellabel.setVisible(true);
            nameLabel2.setVisible(true);
            pl2ang.setVisible(true);
            pl2vec.setVisible(true);
        }
    }
}