package com.client.pane.game;

import com.client.controller.StageController;
import com.client.controller.spaceCreate.SpaceFxmlType;
import com.client.game.Managers.SpaceManager;
import com.client.game.Spaces.ISpace;
import com.client.game.Spaces.SpaceCreation.ISpaceCreatorFactory;
import com.client.game.Spaces.SpaceCreation.NormalCreation;
import com.client.pane.game.BoardSpace;
import com.client.pane.game.Player.IPlayer;
import com.client.pane.game.Player.Player;
import com.client.pane.game.space.NotPurchasableSpace.GoJail;
import com.client.pane.game.space.NotPurchasableSpace.IncomeTax;
import com.client.pane.game.space.NotPurchasableSpace.JailVisit;
import com.client.pane.game.space.NotPurchasableSpace.StartingPoint;
import com.client.pane.game.space.PurchasableSpace.Property;
import com.client.pane.game.space.PurchasableSpace.RailFerrySpace;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import javafx.event.ActionEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class GameBoard {


    @FXML
    GridPane gameBoardGrid;

    @FXML
    Button rollButton;
    @FXML
    private ImageView diceImage1;

    @FXML
    private ImageView diceImage2;

    private List<BoardSpace> spaces = new ArrayList<>();
    private List<IPlayer> players = new ArrayList<>();
    private List<ImageView> images = new ArrayList<>();

    int playerTurn = 0;

    @FXML
    public  void  rollPressed(ActionEvent event){

        roll();

    }

    private void roll() {
        rollButton.setDisable(true);
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
                movePlayer(dice1Val + dice2Val);
                playerTurn = (playerTurn + 1) % 2;


            }
        });

        taskThread.start();
    }

    public void movePlayer(int dice) {

        final int  a = dice;

        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                IPlayer player = players.get(playerTurn);
                int startPos = player.getPosition();
                for(int i = 0; i < a; i++){

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {

                    }
                    final int d = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            spaces.get((d + startPos) % spaces.size()).removeImage(images.get(playerTurn));
                            spaces.get((d + startPos + 1) % spaces.size()).putImage(images.get(playerTurn));
                        }
                    });
                }

                player.movePlayer(a);
                if(playerTurn == 1) {


                }
                else{
                    rollButton.setDisable(false);
                }
                System.out.println(player.getName() + player.getMoney());

            }
        });

        taskThread.start();



    }





    @FXML
    public void initialize() {


        setImages();

        constructSpaces();

        setPlayers();


        for(BoardSpace space : spaces){
           gameBoardGrid.add(space , space.getGridX() , space.getGridY());
        }

       startGame();



    }

    private void startGame() {



    }

    private void setPlayers() {
        IPlayer player1 = new Player("Sait");
        IPlayer player2 = new Player("Sait2");

        players.add(player1);
        players.add(player2);

        spaces.get(players.get(0).getPosition()).putImage(images.get(0));
        spaces.get(players.get(1).getPosition()).putImage(images.get(1));
    }

    private void setImages() {
        try {
            InputStream stream1 = new FileInputStream("C:\\Users\\Sait\\Desktop\\SE\\Ceng453-TermProject-Group-4-frontend\\src\\main\\resources\\images\\duck.jpg");
            InputStream stream2 = new FileInputStream("C:\\Users\\Sait\\Desktop\\SE\\Ceng453-TermProject-Group-4-frontend\\src\\main\\resources\\images\\reyiz.jpg");
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
        spaces.add(new BoardSpace(width / 5 , height /5 , new StartingPoint() , 0, 4) );
        spaces.add(new BoardSpace(width / 5 , height /5 , new com.client.pane.game.space.PurchasableSpace.Property("Ankara" , 500) , 0, 3) );
        spaces.add(new BoardSpace(width / 5 , height /5 , new Property("Diyarbakır" , 1000) , 0, 2) );
        spaces.add(new BoardSpace(width / 5 , height /5 , new RailFerrySpace("RailRoad 1" , 500) , 0, 1) );
        spaces.add(new BoardSpace(width / 5 , height /5 , new JailVisit() , 0 , 0));
        spaces.add(new BoardSpace(width / 5 , height /5 , new Property("Trabzon" , 500) , 1 , 0));
        spaces.add(new BoardSpace(width / 5 , height /5 , new Property("Ordu" , 500) , 2 , 0));
        spaces.add(new BoardSpace(width / 5 , height /5 , new RailFerrySpace("Ferry 1" , 500) , 3 , 0));
        spaces.add(new BoardSpace(width / 5 , height /5 , new IncomeTax(), 4 , 0));
        spaces.add(new BoardSpace(width / 5 , height /5 , new Property("Muş" , 500) , 4 , 1));
        spaces.add(new BoardSpace(width / 5 , height /5 , new Property("Ağri" , 500) , 4 , 2));
        spaces.add(new BoardSpace(width / 5 , height /5 , new RailFerrySpace("RailRoad 2" , 500) , 4, 3) );
        spaces.add(new BoardSpace(width / 5 , height /5 , new GoJail() , 4, 4) );
        spaces.add(new BoardSpace(width / 5 , height /5 , new Property("İzmir" , 500) , 3 , 4));
        spaces.add(new BoardSpace(width / 5 , height /5 , new Property("Denizli" , 500) , 2 , 4));
        spaces.add(new BoardSpace(width / 5 , height /5 , new RailFerrySpace("Ferry 2" , 500) , 1, 4) );
    }




}



