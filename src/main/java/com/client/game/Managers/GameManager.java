package com.client.game.Managers;

import com.client.controller.gameboard.GameBoardController;
import com.client.controller.gameboard.sceneTypes.IPrepareScene;
import com.client.controller.observer.IObservable;
import com.client.controller.observer.IObserverText;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public  class GameManager  implements IObservable {
    // this class manager of the game.
    // this class is determining next player or game finish or not
    // also this class is do some connection between space and player
    // this class is single and needed to reached from other classes so we use singleton

    private  static  GameManager instance = null;

    private  List<IPlayer> players = new ArrayList<>(); // players of the game
    private HashMap<IPlayer, IPrepareScene> sceneType = new HashMap<>(); // hash map betweeen player and player scene type
    private int playerNumber = 0; // active Player  index
    private GameBoardController gameBoardController;
    private SpaceDeed currentDeed;
    private  int dice1;
    private  int dice2;
    private List<IObserverText> observables = new ArrayList<>(); // observers of the Game Manager for GUI

    public  static  synchronized    GameManager getInstance(){
        if(instance == null){
            instance = new GameManager();
        }
        return  instance;
    }

    public  static  synchronized    void destroy(){ // destroy the game

        instance = null;

    }

    public  void  addPlayer(IPlayer player, IPrepareScene sceneType){ // add player
            players.add(player);
            this.sceneType.put(player,sceneType);
    }



    public  IPlayer getActivePlayer(){ return  players.get(playerNumber); } // return active player


    public  IPlayer getPlayerIndex(int i){ return  players.get(i);} // return active player with index

    public  void  setGameBoard(GameBoardController gameBoardController){ this.gameBoardController = gameBoardController;} // set game board

    public  int getActivePlayerTurn() { return  playerNumber;}  // get active player turn

    public GameBoardController getGameBoard(){return gameBoardController; }

    public  IPrepareScene getSceneType(){return   sceneType.get(players.get(playerNumber)); } // get scene type of according to the player

    public  void  setDeed(SpaceDeed spaceDeed){ this.currentDeed = spaceDeed;}



    public  void  nextTurn(){ // end turn botton is finished so next turn will be started. determine next player and prepare scene
        playerTurn();  // determine next player
        IPlayer activePlayer = getActivePlayer();
        if(!gameFinishCheck()){ // check whether the game is finished or not
            notifyObservers();
            if(activePlayer.isPrison()){ // player not in prison so prepare scene according to the its type
                sceneType.get(players.get(playerNumber)).nextTurnJail(gameBoardController);

            }else{ // prepare prison scene since player in prison
                sceneType.get(players.get(playerNumber)).nextTurnNormal(gameBoardController);

            }

        }else{ // finish the game
                gameBoardController.endGame();
        }



    }

    public void  play(int dice1 , int dice2){ // roll button is pressed and movement animation finished so player should do animation
        this.dice1 = dice1;
        this.dice2 = dice2;
        int rollValue = dice1 + dice2;

        IPlayer activePlayer = players.get(playerNumber); // active player
        activePlayer.movePlayer(rollValue); // set new position of player
        activePlayer.action( SpaceManager.getInstance().getSpace(activePlayer.getPosition()),dice1,dice2); // player will make action


    }

    private  void  playerTurn(){ // turn finished so determine who is next player
        if(getActivePlayer().isNextTurn()){ // last player not roll double so change player
            playerNumber = (playerNumber + 1) % 2;
        }


    }

    public  void  purchaseAction(){ // player pressed purchase button so purchase the space
        boolean purchase = getActivePlayer().purchaseSpace(currentDeed.getCost());
        if(purchase){

            currentDeed.purchase(getActivePlayer());
        }

    }



    public  void  goJail(){ // player in Go Jail space so player will go jail

        gameBoardController.goJail();  // set image of the player in jail space in GUI
        int newPos = 4 - getActivePlayer().getPosition(); // new position of the player
        getActivePlayer().movePlayer(newPos); // set jail Position of the player
    }


    public  void  endTurn(){ // set end turn button

        sceneType.get(players.get(playerNumber)).endTurn();


    }

    public  void  jailTime(){ // jail time button is pressed
        IPlayer activePlayer = getActivePlayer();
        activePlayer.action( SpaceManager.getInstance().getSpace(4),dice1,dice2);
        endTurn();

    }



    public  boolean  gameFinishCheck(){ // check whether the game is over  or not
        for (IPlayer player: players) {
            if(player.getMoney() < 0){
                return  true;
            }

        }
        return  false;
    }


    @Override
    public void notifyObservers() { // notify observers in user information GUI when turn ended

        for (IObserverText observable:observables) {
            observable.updateOwner(getActivePlayer());
        }
    }

    @Override
    public void addUserInformation(IObserverText observerText) { // add user information observers
        observables.add(observerText);
    }
}
