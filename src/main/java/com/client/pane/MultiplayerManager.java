package com.client.pane;

import com.client.ClientApplication;
import com.client.backendRequest.MultiplayerRequest;
import com.client.controller.StageController;
import com.client.controller.gameboard.sceneTypes.MultiPlayerScene;
import com.client.controller.gameboard.sceneTypes.PlayerScene;
import com.client.dto.jsonObj.Action;
import com.client.dto.jsonObj.GameObj;
import com.client.game.Managers.GameManager;
import com.client.game.Managers.GameType;
import com.client.pane.game.GameBoard;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.player.MultiPlayer;
import com.client.pane.game.player.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;


/**
 * This class is handling multiplayer games requests
 */
public class MultiplayerManager {

    private  static MultiplayerManager instance = null;
    private MultiplayerRequest multiplayerRequest;
    private GameBoard gameBoard;
    private  Thread taskThread = null;
    private  String lastAction = ""; // last action of the game

    public  static  synchronized    MultiplayerManager getInstance(){
        if(instance == null){
            instance = new MultiplayerManager();
        }
        return  instance;
    }

    public  MultiplayerManager(){
        multiplayerRequest = new MultiplayerRequest();
    }


    /**
     *
     * @param gameBoard gameboard of the game
     */
    public  void  SetGameBoard(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    /**
     *  when game turn is on the player send information to the database
     * @param type action type
     * @param dice1 dice value of the roll
     * @param dice2 dice value of the roll
     */
    public  void  sendInformation( String type , int dice1 , int dice2 ){
        System.out.println(Session.name + " information gönderdim");
        System.out.println("gönderdiğim infor " + type);
          multiplayerRequest.addAction(Session.name,type,dice1,dice2,Session.token);
    }


    /**
     * when game turn on other player get information from the data base and set the informations
     * this method is starting taking informations from data base with a thread
     */
    public  void  startGetInformation(){
        if(taskThread != null) return;
        System.out.println(Session.name + " information almaya başladım");
         taskThread = new Thread(new Runnable() {

            @Override
            public void run() {

                while(true){

                    getInformation();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }

                }


            }
        });
        taskThread.start();
    }


    /**
     * getting information from database
     * according the action type make changes in game
     */
    public  void  getInformation(){

        ResponseEntity<String> response = multiplayerRequest.getAction(Session.name, Session.token);
        ObjectMapper mapper = new ObjectMapper();
        Action action = null;
        if(response.getStatusCode() == HttpStatus.OK){
            try {
                action = mapper.readValue(response.getBody() , Action.class);
             //   System.out.println("info aldım " + action.getType());
                if(action.getName().equals(Session.name)) return;
                if(action.getType().equals(lastAction)) return;
                lastAction = action.getType();
                deleteInformation(); // delete info
             //   System.out.println("aldığım info " +  action.getType());
                if(action.getType().equals("roll")){
                    rollAction(action);
                }
                else if(action.getType().equals("purchase")){
                    purchaseAction(action);
                }
                else if(action.getType().equals("jailTime")){
                    jailTimeAction(action);
                }else if(action.getType().equals("nextTurn")){
                    nextTurnAction(action);
                }
                else if(action.getType().equals("gameFinish")){
                    gameFinishAction(action);
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }

    /**
     *  Game finish in the other player game so finish the game
     * @param action current action which is taken from database
     */
    private void gameFinishAction(Action action) {
      //  System.out.println("Game finish infosu aldım");
        gameBoard.endGame();
        gameFinish();
    }


    /**
     *  after making action according to the data base value delete last information from database
     */
    public  void  deleteInformation(){
       // System.out.println("Son Actionu Siliyorum");
        multiplayerRequest.deleteAction(Session.name,Session.token);
    }


    /**
     * other player roll so make movement according to the roll
     * @param action current action which is taken from database
     */
    public  void  rollAction(Action action){
        //    System.out.println(Session.name + " hareket ediyorum bilgi geldi");
         //   System.out.println("gelen bilgi " + action.getDice1() + " " + action.getDice2());
            gameBoard.movePlayer(action.getDice1(),action.getDice2()); // move player
            //GameManager.getInstance().play(action.getDice1(),action.getDice2());
    }

    /**
     * turn of the other player is finished so change the turn
     * @param action current action which is taken from database
     */
    public  void  nextTurnAction(Action action){
      //  System.out.println(Session.name + " next turn bilgisi");
        GameManager.getInstance().nextTurn();
    }

    /**
     * other player make purchase action
     * @param action current action which is taken from database
     */
    public  void  purchaseAction(Action action){
       // System.out.println(Session.name + " purhcase bilgisi geldi");
        GameManager.getInstance().purchaseAction();
    }

    /**
     * other player sit in jail one round
     * @param action current action which is taken from database
     */
    public  void  jailTimeAction(Action action){
       // System.out.println(Session.name + " jail time bilgisi");
        GameManager.getInstance().jailTime();
    }

    /**
     *  turn is this player so stop taking request from database
     */
    public  void  stopRequest(){
        if(taskThread == null) return;
       // System.out.println("Requesti Durdurdum");
        taskThread.interrupt();
        taskThread = null;
    }

    /**
     * delete game information from database
     */
    public  void  gameFinish(){
       // System.out.println("game finish");
       multiplayerRequest.deleteAllGames(Session.token);
    }


}
