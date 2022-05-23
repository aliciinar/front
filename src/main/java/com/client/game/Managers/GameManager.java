package com.client.game.Managers;

import com.client.pane.game.BoardSpace;
import com.client.pane.game.GameBoard;
import com.client.pane.game.IPrepareScene;
import com.client.pane.game.Player.IPlayer;
import com.client.pane.game.space.ISpace;
import com.client.pane.game.space.PurchasableSpace.SpaceDeed;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {
    private  static  GameManager instance = null;
    private  List<IPlayer> players = new ArrayList<>();
    private HashMap<IPlayer, IPrepareScene> sceneType = new HashMap<>();
    int playerNumber = 0; // active Player
    private  GameBoard gameBoard;
    private SpaceDeed currentDeed;
    public  static  synchronized    GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return  instance;

    }



   /* public  void  game(){
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

    }*/

    public  void  addPlayer(IPlayer player, IPrepareScene sceneType){
            players.add(player);
            this.sceneType.put(player,sceneType);
    }
    private  int dice1;
    private  int dice2;
    public void  play(int dice1 , int dice2){
       // this.dice1 = dice1;
       // this.dice2 = dice2;
        System.out.println("roll buttonu basıldı play functionu gameManager" + " dice1 "+ dice1 + " dice2 " + dice2);
        this.dice1 = dice1;
        this.dice2 = dice2;
        int rollValue = dice1 + dice2;


        IPlayer activePlayer = players.get(playerNumber);
       activePlayer.movePlayer(rollValue); // set new position
       // activePlayer.movePlayer(2); // set new position
       // System.out.println("space" + SpaceManager.getInstance().getSpace(activePlayer.getPosition()).getName());
        activePlayer.action( SpaceManager.getInstance().getSpace(activePlayer.getPosition()),dice1,dice2);
    //    SpaceManager.getInstance().getSpace(activePlayer.getPosition()).action(activePlayer);// do action
       // System.out.println("hareket bitti");
        //System.out.println(activePlayer.getName());
       // System.out.println(activePlayer.getMoney());

       //nextTurn();

    }

    private  void  playerTurn(){
        System.out.println("GamaManager player turn functionu -----------------"+ " yeni player: " + activePlayer().getName());
        if(activePlayer().isNextTurn()){
            System.out.println("yeniplayer");
            playerNumber = (playerNumber + 1) % 2;
        }else{
            System.out.println("eski player devamke");

        }


    }

    public  IPlayer activePlayer(){ return  players.get(playerNumber); }

    public  void  purchaseSpace(){

    }





    public  IPlayer getPlayerIndex(int i){ return  players.get(i);}

    public  void  setGameBoard(GameBoard gameBoard){ this.gameBoard = gameBoard;}

    public  int  activePlayerTurn() { return  playerNumber;}

    public  void  nextTurn(){
        System.out.println("game manager nextTurn fonksiyonuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        playerTurn();
        IPlayer activePlayer = activePlayer();
        System.out.println(activePlayer.getName() + "game manager nextTurn fonksiyonuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        System.out.println("player hapistemi abiii: " + activePlayer.isPrison());
       /* if(activePlayer.isPrison()){
            activePlayer.action( SpaceManager.getInstance().getSpace(activePlayer.getPosition()),dice1,dice2);
            nextTurn();
        }else{
            sceneType.get(players.get(playerNumber)).nextTurnNormal(gameBoard);
        }*/
        if(activePlayer.isPrison()){
            sceneType.get(players.get(playerNumber)).nextTurnJail(gameBoard);

        }else{
            sceneType.get(players.get(playerNumber)).nextTurnNormal(gameBoard);

        }

        //gameBoard.prepareScene(sceneType.get(players.get(playerNumber)));
        //playerTurn();
    }

    public  GameBoard getGameBoard(){return gameBoard; }

    public  IPrepareScene getSceneType(){return   sceneType.get(players.get(playerNumber)); }

    public  void  setDeed(SpaceDeed spaceDeed){ this.currentDeed = spaceDeed;}

    public  void  purchaseAction(){
        boolean purchase = activePlayer().purchaseSpace(currentDeed.GetCost());
        if(purchase){

            currentDeed.Purchase(activePlayer());
        }else{

        }
      //  System.out.println("player" + activePlayer().getName() + " money: " + activePlayer().getMoney());

    }

    public  void  purchaseActionBot(){
        boolean purchase = activePlayer().purchaseSpace(currentDeed.GetCost());
        if(purchase){
            currentDeed.Purchase(activePlayer());
        }else{

        }

        nextTurn();
    }

    public  void  goJail(){

        gameBoard.goJail();
        //GameManager.getInstance().getSceneType().endTurn();
      //todo  nextTurn();
    }


    public  void  endTurn(){
        sceneType.get(players.get(playerNumber)).endTurn();
    }

    public  void  jailTime(){
        IPlayer activePlayer = activePlayer();
        activePlayer.action( SpaceManager.getInstance().getSpace(4),dice1,dice2);
        endTurn();

    }


}
