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
import java.util.HashMap;

public class MultiplayerManager {

    private  static MultiplayerManager instance = null;
    private MultiplayerRequest multiplayerRequest;
    private GameBoard gameBoard;

    public  static  synchronized    MultiplayerManager getInstance(){
        if(instance == null){
            instance = new MultiplayerManager();
        }
        return  instance;
    }

    public  MultiplayerManager(){
        multiplayerRequest = new MultiplayerRequest();
    }

    public  void  SetGameBoard(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    public  void  sendInformation( String type , int dice1 , int dice2 ){
        System.out.println(Session.name + " information gönderdim");
        System.out.println(dice1 + " " + dice2);
          multiplayerRequest.addAction(Session.name,type,dice1,dice2,Session.token);
    }


    private  Thread taskThread = null;
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

    private  String lastAction = "";

    public  void  getInformation(){

        ResponseEntity<String> response = multiplayerRequest.getAction(Session.name, Session.token);
        ObjectMapper mapper = new ObjectMapper();
        Action action = null;
        if(response.getStatusCode() == HttpStatus.OK){
            try {
                action = mapper.readValue(response.getBody() , Action.class);
                if(action.getName().equals(Session.name)) return;
                if(action.getType().equals(lastAction)) return;
                lastAction = action.getType();
                deleteInformation(); // delete info
                System.out.println("aldığım info " +  action.getType());
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
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }


    public  void  deleteInformation(){
        System.out.println("Son Actionu Siliyorum");
        multiplayerRequest.deleteAction(Session.name,Session.token);
    }



    public  void  rollAction(Action action){
            System.out.println(Session.name + " hareket ediyorum bilgi geldi");
            System.out.println("gelen bilgi " + action.getDice1() + " " + action.getDice2());
            gameBoard.movePlayer(action.getDice1(),action.getDice2()); // move player
            //GameManager.getInstance().play(action.getDice1(),action.getDice2());
    }

    public  void  nextTurnAction(Action action){
        System.out.println(Session.name + " next turn bilgisi");
        GameManager.getInstance().nextTurn();
    }

    public  void  purchaseAction(Action action){
        System.out.println(Session.name + " purhcase bilgisi geldi");
        GameManager.getInstance().purchaseAction();
    }

    public  void  jailTimeAction(Action action){
        System.out.println(Session.name + " jail time bilgisi");
        GameManager.getInstance().jailTime();
    }

    public  void  stopRequest(){
        if(taskThread == null) return;
        System.out.println("Requesti Durdurdum");
        taskThread.interrupt();
        taskThread = null;
    }



}
