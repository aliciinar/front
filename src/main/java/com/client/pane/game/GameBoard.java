package com.client.pane.game;

import com.client.ClientApplication;
import com.client.backendRequest.MultiplayerRequest;
import com.client.controller.StageController;
import com.client.controller.gameboard.UserInformationController;
import com.client.controller.gameboard.sceneTypes.MultiPlayerScene;
import com.client.game.Managers.GameManager;
import com.client.game.Managers.GameType;
import com.client.game.Managers.SpaceManager;
import com.client.pane.MainMenu;
import com.client.pane.MultiplayerManager;
import com.client.pane.Session;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.player.UserType;
import com.client.pane.game.space.spaceCreation.ISpaceCreatorFactory;
import com.client.pane.game.space.spaceCreation.NormalCreation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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


/**
 * This class is called when player click play game button
 * It connects every subcomponent of the game such as Player info, Game spaces, and game buttons.
 */
public class GameBoard {

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

    void setButton(Button button) {
        button.setStyle(MainMenu.IDLE_BUTTON_STYLE);
        button.setOnMouseEntered(e -> button.setStyle(MainMenu.HOVERED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(MainMenu.IDLE_BUTTON_STYLE));
    }

    /**
     * initialize the screen
     */
    @FXML
    public void initialize() { // initialize the game

        setButton(rollButton);
        setButton(purchaseButton);
        setButton(endTurnButton);
        setButton(playTimeInJail );
        StageController.screenController.getScene().setOnKeyPressed(e -> { // event for CTRL + 9 event. Finish the game when this event called

            if (e.getCode() == KeyCode.DIGIT9 && e.isControlDown() ) {
                if(GameManager.getInstance().getGameType() == GameType.Multiplayer ) {
                    if( GameManager.getInstance().getActivePlayer().getName().equals(Session.name)){
                        System.out.println("name of the player " + GameManager.getInstance().getActivePlayer().getName());
                        forceFinish();
                    }
                }
                else{
                    forceFinish();
                }


            }
        });

        constructSpaces();
        userInformation.setAlignment(Pos.CENTER);

        GameManager.getInstance().setGameBoard(this);
        setImages();
        setPlayers();
        setUserInformation();
        if(GameManager.getInstance().getGameType() == GameType.Multiplayer){
            MultiplayerManager.getInstance().SetGameBoard(this);
            if(Session.name == GameManager.getInstance().getPlayerIndex(0).getName()){
                activateButtons(false , true , true , true);
            }
            else{
                activateButtons(true , true , true , true);
                MultiplayerManager.getInstance().startGetInformation();

            }

        }else{
            activateButtons(false , true , true , true);
        }
        for(BoardSpace space : spaces){
            gameBoardGrid.add(space , space.getGridX() , space.getGridY());
        }



    }


    private  void  forceFinish(){
        int imageIndex = GameManager.getInstance().getActivePlayerTurn();
        IPlayer activePlayer = GameManager.getInstance().getActivePlayer();
        spaces.get((activePlayer.getPosition()) % spaces.size()).removeImage(images.get(imageIndex));
        spaces.get((8) % spaces.size()).putImage(images.get(imageIndex));
        activePlayer.moneyTransition(-10000000);

        GameManager.getInstance().forceFinishGame();
        endGame();
    }

    /**
     * purchase button event
     * @param event
     */

    @FXML
    public  void  purchasePressed(ActionEvent event){
        activateButtons(true, true,false,true);
        GameManager.getInstance().purchaseAction();
    }

    /**
     * wait in jail one time
     * @param event jail time button event
     */
    @FXML
    public  void  jailTime(ActionEvent event){
        GameManager.getInstance().jailTime();
    }


    /**
     * end turn button event
     * prepare scene for next round
     * @param event end turn button event
     */
    @FXML
    public  void  endTurnPressed(ActionEvent event){

        GameManager.getInstance().nextTurn();

    }


    /**
     * roll button event
     * @param event roll button event
     */

    @FXML
    public  void  rollPressed(ActionEvent event){
        activateButtons(true,true,true,true);
        roll(); // roll the dice

    }

    /**
     * game finished prepare score scene
     */
    public void endGame() {

        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {




                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
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
                    });



            }
        });

        taskThread.start();
        try {
            taskThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * activate buttons
     * @param rollButtonSet rollbutton
     * @param purchaseButtonSet purchaseButton
     * @param endTurnButtonSet endTurnButton
     * @param jailTime jailTimeButton
     */
    public   void  activateButtons(boolean rollButtonSet , boolean purchaseButtonSet , boolean endTurnButtonSet, boolean jailTime){
        rollButton.setDisable(rollButtonSet);
        purchaseButton.setDisable(purchaseButtonSet);
        endTurnButton.setDisable(endTurnButtonSet);
        playTimeInJail.setDisable(jailTime);
    }

    /**
     *  animation for roll animation
     */
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
                 //  dice1Val = 6;       //jail and roll again
              //  dice2Val = 6;
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
                if(GameManager.getInstance().getGameType() == GameType.Multiplayer && GameManager.getInstance().getActivePlayer().getUserType() == UserType.User){
                    System.out.println("gönderdiğim bilgi " + dice1Val + " "+ dice2Val);
                    MultiplayerManager.getInstance().sendInformation("roll", dice1Val,dice2Val);
                }
                movePlayer(dice1Val, dice2Val);
                playerTurn = (playerTurn + 1) % 2;
               // MultiplayerRequest multiplayerRequest = new MultiplayerRequest();
              //  multiplayerRequest.addAction(Session.name,"roll",dice1Val,dice2Val,Session.token);



            }
        });
        taskThread.start();
    }

    /**
     * animation for move. Players move according to the dice values
     * @param dice1 dice value
     * @param dice2  dice value
     */
    public void movePlayer(int dice1, int dice2) {

        final int  diceVal = dice1 + dice2;
        final  int dice1Val = dice1;
        final  int dice2Val = dice2;
        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                IPlayer player = GameManager.getInstance().getActivePlayer();
                int startPos = player.getPosition();
                int imageIndex = GameManager.getInstance().getActivePlayerTurn();
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
                GameManager.getInstance().play(dice1Val, dice2Val);


            }
        });

        taskThread.start();
        try {
            taskThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    /**
     * set player GUI in jail
     */
    public void goJail(){


        IPlayer player = GameManager.getInstance().getActivePlayer();
        int startPos = player.getPosition();
        int imageIndex = GameManager.getInstance().getActivePlayerTurn();
      //  int newPos = 4 - player.getPosition();


        spaces.get((startPos) % spaces.size()).removeImage(images.get(imageIndex));
        spaces.get((4) % spaces.size()).putImage(images.get(imageIndex));
        if(GameManager.getInstance().getGameType() == GameType.Multiplayer){
            if(GameManager.getInstance().getActivePlayer().getName().equals(Session.name)){
                activateButtons(true,true,false,true);

            }else{
                activateButtons(true,true,true,true);
            }

        }else{
            activateButtons(true,true,false,true);

        }

    }

    /**
     * set players
     */
    private void setPlayers() {
        spaces.get(GameManager.getInstance().getPlayerIndex(0).getPosition()).putImage(images.get(0));
        spaces.get(GameManager.getInstance().getPlayerIndex(1).getPosition()).putImage(images.get(1));
    }

    /**
     * set ımages
     */
    private void setImages() {
        try {
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

    /**
     * set user ınformations
     */
    private  void  setUserInformation(){
        UserInformationController user1 = new UserInformationController(GameManager.getInstance().getPlayerIndex(0));
        UserInformationController user2 = new UserInformationController(GameManager.getInstance().getPlayerIndex(1));
        PlayerTurn playerTurnController = new PlayerTurn(GameManager.getInstance().getActivePlayer());

        userInformation.getChildren().add(user1);
        userInformation.getChildren().add(user2);
        userInformation.getChildren().add(playerTurnController);

    }

    /**
     * set baord spaces
     */
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



