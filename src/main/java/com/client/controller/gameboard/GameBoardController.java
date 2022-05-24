package com.client.controller.gameboard;

import com.client.game.Managers.GameManager;
import com.client.game.Managers.SpaceManager;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.SpaceCreation.ISpaceCreatorFactory;
import com.client.pane.game.space.SpaceCreation.NormalCreation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class GameBoardController {

    @FXML
    Button rollButton , purchaseButton , endTurnButton;


    @FXML
    GridPane gameBoardGrid;


    @FXML
    private ImageView diceImage1;

    @FXML
    private ImageView diceImage2;
    @FXML
    Button playTimeInJail;

    private List<BoardSpace> spaces = new ArrayList<>();
    private List<ImageView> images = new ArrayList<>();

    int playerTurn = 0;

    @FXML
    public void initialize() {
        constructSpaces();

        GameManager.getInstance().setGameBoard(this);
        setImages();
        setPlayers();

        for(BoardSpace space : spaces){
            gameBoardGrid.add(space , space.getGridX() , space.getGridY());
        }



    }

    @FXML
    public  void  endTurnPressed(ActionEvent event){
        //endTurn();
        System.out.println("Game Board End Turn Pressed");
        GameManager.getInstance().nextTurn();

    }

    @FXML
    public  void  purchasePressed(ActionEvent event){
        System.out.println("Game Board purchase Pressed");
        activateButtons(true, true,false,true);
        GameManager.getInstance().purchaseAction();
    }

    @FXML
    public  void  jailTime(ActionEvent event){
        GameManager.getInstance().jailTime();
    }

    @FXML
    public  void  endTurnPressed(ActionEvent event){
        //endTurn();
        System.out.println("Game Board End Turn Pressed");
        GameManager.getInstance().nextTurn();

    }

    @FXML
    public  void  purchasePressed(ActionEvent event){
        System.out.println("Game Board purchase Pressed");
        activateButtons(true, true,false,true);
        int pos = GameManager.getInstance().getActivePlayer().getPosition();
        GameManager.getInstance().purchaseAction();
    }

    @FXML
    public  void  jailTime(ActionEvent event){
        GameManager.getInstance().jailTime();
    }


    @FXML
    public  void  rollPressed(ActionEvent event){
        activateButtons(true,true,true,true);
        roll();

    }


    public   void  activateButtons(boolean rollButtonSet , boolean purchaseButtonSet , boolean endTurnButtonSet, boolean jailTime){
        rollButton.setDisable(rollButtonSet);
        purchaseButton.setDisable(purchaseButtonSet);
        endTurnButton.setDisable(endTurnButtonSet);
        playTimeInJail.setDisable(jailTime);
    }

    public void roll() {

        activateButtons(true,true,true,true);

        Thread taskThread = new Thread(new Runnable() {
            int dice1Val = 0 ;
            int dice2Val = 0;
            @Override
            public void run() {
                for(int i = 0; i < 20; i++){

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {

                    }
                    Random random = new Random();
                  dice1Val = random.nextInt(6)+1;
                  dice2Val = random.nextInt(6)+1;
                   // dice1Val = 1; // three time double test
                  // dice2Val = 1;
                 //   dice1Val = 6;       //jail and roll again
               //    dice2Val = 6;

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            File file1 = new File("src/main/resources/images/Dice" + (dice1Val)+".png");
                            File file2 = new File("src/main/resources/images/Dice" + (dice2Val)+".png");
                            diceImage1.setImage(new Image(file1.toURI().toString()));
                            diceImage2.setImage(new Image(file2.toURI().toString()));

                        }
                    });
                }
                movePlayer(dice1Val, dice2Val);
                playerTurn = (playerTurn + 1) % 2;


            }
        });
        taskThread.start();
    }
    public void movePlayer(int dice1, int dice2) {

        final int  diceVal = dice1 + dice2;
        final  int dice1Val = dice1;
        final  int dice2Val = dice2;
        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                IPlayer player = GameManager.getInstance().getActivePlayer(); // active player of the game
                int startPos = player.getPosition();
                int imageIndex = GameManager.getInstance().getActivePlayerTurn();
               // System.out.println("Hareket eden kral"  + player.getName()+ "pozisyonym: " + startPos);
                for(int i = 0; i < diceVal; i++){

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                    final int d = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            spaces.get((d + startPos) % spaces.size()).removeImage(images.get(imageIndex));
                            spaces.get((d + startPos + 1) % spaces.size()).putImage(images.get(imageIndex));
                        }
                    });
                }
                //System.out.println("Move bitti");
                GameManager.getInstance().play(dice1Val, dice2Val); // make actions in player according to the dice value


            }
        });

        taskThread.start();
        try {
            taskThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    public void goJail(){


        IPlayer player = GameManager.getInstance().getActivePlayer(); // active player of the game
        int startPos = player.getPosition();
        int imageIndex = GameManager.getInstance().getActivePlayerTurn();
        // todo hapse giderken buralar yorum satırına alındı sıkıntı çıkarsa diye
      //  int newPos = 4 - player.getPosition();


        spaces.get((startPos) % spaces.size()).removeImage(images.get(imageIndex));
        spaces.get((4) % spaces.size()).putImage(images.get(imageIndex));
        //player.movePlayer(newPos);
        activateButtons(true,true,false,true);
    }


    private void setPlayers() {
        spaces.get(GameManager.getInstance().getPlayerIndex(0).getPosition()).putImage(images.get(0));
        spaces.get(GameManager.getInstance().getPlayerIndex(1).getPosition()).putImage(images.get(1));
    }

    private void setImages() {
        try {
            InputStream stream1 = new FileInputStream("C:\\Users\\Sait\\Desktop\\SE\\Ceng453-TermProject-Group-4-frontend\\src\\main\\resources\\images\\duck.jpg");
            InputStream stream2 = new FileInputStream("C:\\Users\\Sait\\Desktop\\SE\\Ceng453-TermProject-Group-4-frontend\\src\\main\\resources\\images\\reyiz.jpg");
          //  InputStream stream1 = new FileInputStream("D:\\Yüksek Lisans\\SoftwareConstruction\\FrontEnd\\Ceng453-TermProject-Group-4-frontend\\src\\main\\resources\\images\\duck.jpg");
          //  InputStream stream2 = new FileInputStream("D:\\Yüksek Lisans\\SoftwareConstruction\\FrontEnd\\Ceng453-TermProject-Group-4-frontend\\src\\main\\resources\\images\\reyiz.jpg");
            Image image1 = new Image(stream1);
            Image image2 = new Image(stream2);

            ImageView imagePlayer1 = new ImageView();
            ImageView imagePlayer2 = new ImageView();

            imagePlayer1.setImage(image1);
            imagePlayer1.setFitWidth(40);
            imagePlayer1.setPreserveRatio(true);

            imagePlayer2.setImage(image2);
            imagePlayer2.setFitWidth(40);
            imagePlayer2.setPreserveRatio(true);

            images.add(imagePlayer1);
            images.add(imagePlayer2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    private void constructSpaces() {
        double width = 800;
        double height = 800;

        ISpaceCreatorFactory spaceCreatorFactory = new NormalCreation();
        List<NormalCreation.GridCord> spaceInformation = SpaceManager.getInstance().createSpaces(spaceCreatorFactory);

        for(NormalCreation.GridCord space : spaceInformation){

            spaces.add(new BoardSpace(width / 5 , height /5 , space.getSpace() , space.getxCor(), space.getyCor()) );
        }
        SpaceManager.getInstance().setSpaces(spaces);
    }


}



