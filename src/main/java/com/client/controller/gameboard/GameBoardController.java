package com.client.controller.gameboard;

import com.client.ClientApplication;
import com.client.controller.StageController;
import com.client.game.Managers.GameManager;
import com.client.game.Managers.SpaceManager;
import com.client.pane.Session;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.SpaceCreation.ISpaceCreatorFactory;
import com.client.pane.game.space.SpaceCreation.NormalCreation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import javafx.event.ActionEvent;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

public class GameBoardController {

    @FXML
    Button rollButton , purchaseButton , endTurnButton;


    @FXML
    HBox userInformation;

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

        StageController.screenController.getScene().setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.DIGIT9 && e.isControlDown()) {
                int imageIndex = GameManager.getInstance().getActivePlayerTurn();
                IPlayer activePlayer = GameManager.getInstance().getActivePlayer();
                spaces.get((activePlayer.getPosition()) % spaces.size()).removeImage(images.get(imageIndex));
                spaces.get((8) % spaces.size()).putImage(images.get(imageIndex));
                activePlayer.moneyTransition(-10000000);
                endGame();
                System.out.println("A key was pressed");
            }
        });
        constructSpaces();

        GameManager.getInstance().setGameBoard(this);
        activateButtons(false , true , true , true);
        setImages();
        setPlayers();
        setUserInformation();

        for(BoardSpace space : spaces){
            gameBoardGrid.add(space , space.getGridX() , space.getGridY());
        }



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
    public  void  rollPressed(ActionEvent event){
        activateButtons(true,true,true,true);
        roll();

    }

    public void endGame() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game");
        alert.setHeaderText("Game Ended");
        alert.setContentText("Scores: \nPlayer1 : "+ + GameManager.getInstance().getPlayerIndex(0).getMoney() + " \n" + "Player2 : " + GameManager.getInstance().getPlayerIndex(1).getMoney()  );
        alert.showAndWait();
        ClientApplication.request.addScore(GameManager.getInstance().getPlayerIndex(0).getName() , GameManager.getInstance().getPlayerIndex(0).getScore() , Session.token);
        StageController.screenController.removeScreen("Game");
        StageController.screenController.activate("Session");
        GameManager.destroy();
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
                     //   dice1Val = 5;
                      //  dice2Val = 3;
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
           // InputStream stream1 = new FileInputStream("C:\\Users\\Sait\\Desktop\\SE\\Ceng453-TermProject-Group-4-frontend\\src\\main\\resources\\images\\duck.jpg");
           // InputStream stream2 = new FileInputStream("C:\\Users\\Sait\\Desktop\\SE\\Ceng453-TermProject-Group-4-frontend\\src\\main\\resources\\images\\reyiz.jpg");
           InputStream stream1 = new FileInputStream("src/main/resources/images/duck.jpg");
            InputStream stream2 = new FileInputStream("src/main/resources/images/reyiz.jpg");
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


    private  void  setUserInformation(){
        UserInformation user1 = new UserInformation(GameManager.getInstance().getPlayerIndex(0));
        UserInformation user2 = new UserInformation(GameManager.getInstance().getPlayerIndex(1));
        PlayerTurn playerTurn = new PlayerTurn(GameManager.getInstance().getActivePlayer());

       /* VBox turnInformation = new VBox(20);
        turnInformation.setPadding(new Insets(20,5,5,45));
        Label turnWriting = new Label("Turn Of");
        turnWriting.setMinWidth(50);
        turnWriting.setMinHeight(50);
        Label activePlayerTurn = new Label(GameManager.getInstance().getActivePlayer().getName());
        activePlayerTurn.setMinWidth(30);
        activePlayerTurn.setMinHeight(30);
        turnInformation.getChildren().add(turnWriting);
        turnInformation.getChildren().add(activePlayerTurn);*/



        userInformation.getChildren().add(user1);
        userInformation.getChildren().add(user2);
        userInformation.getChildren().add(playerTurn);
     //   userInformation.getChildren().add(turnInformation);

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



