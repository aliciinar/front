package com.client.game.Managers;

import com.client.controller.gameboard.GameBoardController;
import com.client.controller.gameboard.IPrepareScene;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.PurchasableSpace.SpaceDeed;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameManager {
    private  static  GameManager instance = null;
    private  List<IPlayer> players = new ArrayList<>();
    private HashMap<IPlayer, IPrepareScene> sceneType = new HashMap<>();
    private int playerNumber = 0; // active Player
    private GameBoardController gameBoardController;
    private SpaceDeed currentDeed;
    private  int dice1;
    private  int dice2;

    public  static  synchronized    GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return  instance;
    }

    public  void  addPlayer(IPlayer player, IPrepareScene sceneType){
            players.add(player);
            this.sceneType.put(player,sceneType);
    }



    public  IPlayer getActivePlayer(){ return  players.get(playerNumber); }


    public  IPlayer getPlayerIndex(int i){ return  players.get(i);}

    public  void  setGameBoard(GameBoardController gameBoardController){ this.gameBoardController = gameBoardController;}

    public  int getActivePlayerTurn() { return  playerNumber;}

    public GameBoardController getGameBoard(){return gameBoardController; }

    public  IPrepareScene getSceneType(){return   sceneType.get(players.get(playerNumber)); }

    public  void  setDeed(SpaceDeed spaceDeed){ this.currentDeed = spaceDeed;}

    public  void  nextTurn(){
      //  System.out.println("game manager nextTurn fonksiyonuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        playerTurn();
        IPlayer activePlayer = getActivePlayer();
     //   System.out.println(activePlayer.getName() + "game manager nextTurn fonksiyonuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
     //   System.out.println("player hapistemi abiii: " + activePlayer.isPrison());

        if(activePlayer.isPrison()){
            sceneType.get(players.get(playerNumber)).nextTurnJail(gameBoardController);

        }else{
            sceneType.get(players.get(playerNumber)).nextTurnNormal(gameBoardController);

        }


    }

    public void  play(int dice1 , int dice2){ // roll button is pressed and movement animation finished so player should do animation
        this.dice1 = dice1;
        this.dice2 = dice2;
        int rollValue = dice1 + dice2;

        IPlayer activePlayer = players.get(playerNumber); // active player
        activePlayer.movePlayer(rollValue); // set new position of player
        activePlayer.action( SpaceManager.getInstance().getSpace(activePlayer.getPosition()),dice1,dice2);
        // System.out.println("hareket bitti");
        //System.out.println(activePlayer.getName());
        // System.out.println(activePlayer.getMoney());



    }

    private  void  playerTurn(){ // turn finished so determine next player
        // System.out.println("GamaManager player turn functionu -----------------"+ " yeni player: " + activePlayer().getName());
        if(getActivePlayer().isNextTurn()){ // last player not roll double so change player
            // System.out.println("yeniplayer");
            playerNumber = (playerNumber + 1) % 2;
        }else{ // last  player  double roll so do not change active player
            //  System.out.println("eski player devamke");

        }


    }

    public  void  purchaseAction(){
        boolean purchase = getActivePlayer().purchaseSpace(currentDeed.GetCost());
        if(purchase){

            currentDeed.Purchase(getActivePlayer());
        }else{

        }
      //  System.out.println("player" + activePlayer().getName() + " money: " + activePlayer().getMoney());

    }



    public  void  goJail(){

        gameBoardController.goJail();
        int newPos = 4 - getActivePlayer().getPosition();
        getActivePlayer().movePlayer(newPos); // set new Position of the player
        //GameManager.getInstance().getSceneType().endTurn();
       //todo  nextTurn();
    }


    public  void  endTurn(){
        gameFinishCheck(); // check whether the game is over or not
        sceneType.get(players.get(playerNumber)).endTurn();
    }

    public  void  jailTime(){
        IPlayer activePlayer = getActivePlayer();
       // System.out.println("Jail Time Actionu:::" + "player : " + activePlayer.getName());
        activePlayer.action( SpaceManager.getInstance().getSpace(4),dice1,dice2);
        endTurn();

    }



    public  void  gameFinishCheck(){
        for (IPlayer player: players) {
            if(player.getMoney() < 0){
                // game over scene
            }

        }
    }



}
