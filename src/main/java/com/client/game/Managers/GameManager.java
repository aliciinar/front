package com.client.game.Managers;

import com.client.pane.game.GameBoard;
import com.client.pane.game.IPrepareScene;
import com.client.pane.game.Player.IPlayer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {
    private  static  GameManager instance = null;
    private  List<IPlayer> players = new ArrayList<>();
    private HashMap<IPlayer, IPrepareScene> sceneType = new HashMap<>();
    int playerNumber = 0; // active Player
    private  GameBoard gameBoard;
    public  static  synchronized  GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return  instance;

    }



    public  void  game(){
        int playerIndex = 0;
        while (true){
           if( players.get(playerIndex).turn()){
               playerIndex +=1;
               playerIndex %= playerNumber;
           }
           else{
               System.out.println("Game oVer");
           }

        }

    }

    public  void  addPlayer(IPlayer player, IPrepareScene sceneType){
            players.add(player);
            this.sceneType.put(player,sceneType);
    }

    public void  play(int rollValue){
        System.out.println("rollValuee");
        IPlayer activePlayer = players.get(playerNumber);
       activePlayer.movePlayer(rollValue); // set new position
     //   SpaceManager.getInstance().getSpace(rollValue).action(activePlayer); // do action
      nextTurn();


    }

    private  void  playerTurn(){
        playerNumber = (playerNumber + 1) % 2;

    }

    public  IPlayer activePlayer(){ return  players.get(playerNumber); }


    public  IPlayer getPlayerIndex(int i){ return  players.get(i);}

    public  void  setGameBoard(GameBoard gameBoard){ this.gameBoard = gameBoard;}

    public  int  activePlayerTurn() { return  playerNumber;}

    public  void  nextTurn(){
        playerTurn();
        gameBoard.prepareScene(sceneType.get(players.get(playerNumber)));
    }

}
