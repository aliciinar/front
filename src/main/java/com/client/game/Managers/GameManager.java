package com.client.game.Managers;

import com.client.pane.game.GameBoard;
import com.client.controller.gameboard.sceneTypes.IPrepareScene;
import com.client.controller.observer.IObservable;
import com.client.controller.observer.IObserverText;
import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.SpaceDeed;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Controls the game events.
 * This class connect GUI to backend of the game
 * What each method does is understandable from their names.
 * this class manager of the game.
 *  this class is determining next player or game finish or not
 *   also this class is do some connection between space and player
 *    this class is single and needed to reached from other classes so we use singleton
 */
public  class GameManager  implements IObservable {


    private  static  GameManager instance = null;

    private  List<IPlayer> players = new ArrayList<>(); // players of the game
    private HashMap<IPlayer, IPrepareScene> sceneType = new HashMap<>(); // hash map betweeen player and player scene type
    private int playerNumber = 0; // active Player  index
    private GameBoard gameBoardController;
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

    /**
     *  destroy the game
     */
    public  static  synchronized    void destroy(){

        instance = null;

    }

    /**
     * // add player
     * @param player registered player or bot
     * @param sceneType type of scene according to user Type which are user or bot
     */
    public  void  addPlayer(IPlayer player, IPrepareScene sceneType){
            players.add(player);
            this.sceneType.put(player,sceneType);
    }


    /**
     *
     * @return return active player
     */

    public  IPlayer getActivePlayer(){ return  players.get(playerNumber); }

    /**
     *
     * @param i integer
     * @return return active player with index
     */
    public  IPlayer getPlayerIndex(int i){ return  players.get(i);} //

    /**
     * set game board
     * @param gameBoardController
     */
    public  void  setGameBoard(GameBoard gameBoardController){ this.gameBoardController = gameBoardController;}

    /**
     *
     * @return get active player turn
     */
    public  int getActivePlayerTurn() { return  playerNumber;}

    public GameBoard getGameBoard(){return gameBoardController; }

    /**
     *
     * @return get scene type of according to the player
     */
    public  IPrepareScene getSceneType(){return   sceneType.get(players.get(playerNumber)); }

    /**
     *  If a player in a deed which is not purchased set this deed in order to buy that
     * @param spaceDeed  deed which current player is on that
     */

    public  void  setDeed(SpaceDeed spaceDeed){ this.currentDeed = spaceDeed;}

    /**
     *  end turn button is finished so next turn will be started. determine next player and prepare scene
     *  In this method game is checking whether game is finished or not and if not finished scene is prepared according to the user type
     *
     */

    public  void  nextTurn(){
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

    /**
     *  roll button is pressed and movement animation finished so player should  make action
     * @param dice1 first dice value
     * @param dice2 second dice value
     */
    public void  play(int dice1 , int dice2){
        this.dice1 = dice1;
        this.dice2 = dice2;
        int rollValue = dice1 + dice2;

        IPlayer activePlayer = players.get(playerNumber);
        activePlayer.movePlayer(rollValue); // set new position of player
        activePlayer.action( SpaceManager.getInstance().getSpace(activePlayer.getPosition()),dice1,dice2); // player will make action


    }

    /**
     * turn finished so determine who is next player
     */
    private  void  playerTurn(){ //
        if(getActivePlayer().isNextTurn()){ // last player not roll double so change player
            playerNumber = (playerNumber + 1) % 2;
        }


    }

    /**
     * player pressed purchase button so purchase the space
     */

    public  void  purchaseAction(){
        boolean purchase = getActivePlayer().purchaseSpace(currentDeed.getCost());
        if(purchase){

            currentDeed.purchase(getActivePlayer());
        }

    }


    /**
     * player in Go Jail space so player will go jail
     * set image of the player in jail space in GUI
     * set new position of the player
     * set jail Position of the player
     */
    public  void  goJail(){

        gameBoardController.goJail();
        int newPos = 4 - getActivePlayer().getPosition();
        getActivePlayer().movePlayer(newPos);
    }

    /**
     * set end turn button
     */
    public  void  endTurn(){

        sceneType.get(players.get(playerNumber)).endTurn();


    }

    /**
     * jail time button is pressed
     */
    public  void  jailTime(){
        IPlayer activePlayer = getActivePlayer();
        activePlayer.action( SpaceManager.getInstance().getSpace(4),dice1,dice2);
        endTurn();

    }


    /**
     *
     * @return check whether the game is over  or not
     */
    public  boolean  gameFinishCheck(){
        for (IPlayer player: players) {
            if(player.getMoney() < 0){
                return  true;
            }

        }
        return  false;
    }

    /**
     * notify observers in user information GUI when turn ended
     */
    @Override
    public void notifyObservers() {

        for (IObserverText observable:observables) {
            observable.updateOwner(getActivePlayer());
        }
    }

    /**
     * add user information observers
     * @param observerText
     */
    @Override
    public void addUserInformation(IObserverText observerText) {
        observables.add(observerText);
    }
}
