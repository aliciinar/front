package com.client.pane.game.player;

import com.client.controller.observer.IObserverText;
import com.client.pane.game.player.playerActionStates.IPlayerState;
import com.client.pane.game.space.ISpace;
import com.client.pane.game.space.purchasableSpace.RailFerrySpace;


import java.util.ArrayList;
import java.util.List;

/**
 * abstract class of the player. general methods and attributes are implemented in here
 */
public abstract class PlayerAbstract  implements  IPlayer{





    protected int position; // position of the player according to index of the space in space manager
    protected  int score = 0; // score of the player
    protected String name;
    protected int money;
    protected IPlayerState state; // state of the player. player can be in prison, can be in not prison and ca go jail state
    protected int ownedSpecialTile = 0; // count of the owned special tile which are railways and ferry port.
    protected  boolean nextTurn = true; // if it true game manager set other player to active player. if it false this player roll again.
    protected  boolean isPrison = false; // check whether player in prison or not
    protected UserType userType;
    protected  boolean startPassMoneyChange = false; // if we roll double third time and we in pass through start point we have an unneccesary money change
                                                        // this is not sutiable for oop but because of time constraints implementation done like that



    protected static List<IObserverText> playerInformation = new ArrayList<>(); // list of the observers. when there is a change observers will be notified.
    @Override
    public int getMoney() {
        return money;
    }
    protected  List<RailFerrySpace> specialSpaces = new ArrayList<>();


    /**
     * purchase a special tile  which are railways and ferry port.
     * @param railFerrySpace
     */
    @Override
    public void purchaseSpecialTile(RailFerrySpace railFerrySpace){

        specialSpaces.add(railFerrySpace);
        int rent = 25 * specialSpaces.size();
        for (RailFerrySpace spacielSpace: specialSpaces) {
            spacielSpace.setRent(rent,this);
        }
    }


    public String getName() { return name;}

    @Override
    public int getPosition() {return position;}

    @Override
    public boolean isNextTurn() {

        return nextTurn;
    }

    @Override
    public void setNextTurn(boolean nextTurn) {
        this.nextTurn = nextTurn;
    }

    @Override
    public boolean isPrison(){
        return  isPrison;
    }
    @Override
    public void  setPrison(boolean isPrison){
        this.isPrison = isPrison;
    }

    @Override
    public void moneyTransition(int amount){ // change money of the player
        money = money + amount;
        notifyObservers(); // notify GUI
    }

    /**
     * set position of the player according to space index
     * if player pass start point ,ncrease money of the player since it is go through start point but not  stop in start point
     * @param move roll value
     */
    @Override
    public void movePlayer(int move){ //
        if((position + move ) > 17){
            moneyTransition(100);
            startPassMoneyChange = true;
        }
        position = (position + move) % 16;



    }


    /**
     * determine player can buy a space or not
     * @param spacePrice price of the space
     * @return if player can buy return true
     */
    @Override
    public boolean purchaseSpace(int spacePrice) {
        if(getMoney() > spacePrice){
            return  true;
        }
        
        return  false;
    }

    /**
     * action of the player according the roll values
     * determine the state according to the dice values
     * make action with states
     * @param space current space
     * @param diceVal1 value of dice
     * @param diceVal2 value of dice
     */
    @Override
    public void action(ISpace space, int diceVal1, int diceVal2) {
        state =    state.determineState(this,diceVal1,diceVal2);
        state =   state.Play(this,diceVal1,diceVal2,space);


    }


    /**
     * check whether we increase money falsely or not
     * @param jailCheck local jail check value
     */
    @Override
    public  void checkFalseMoneyIncrease(boolean jailCheck){
        if(jailCheck && startPassMoneyChange){
            moneyTransition( -100);
        }
        startPassMoneyChange = false;

    }

    /**
     * add observer
     * @param observerText observer
     */
    @Override
    public void  addUserInformation(IObserverText observerText){
        playerInformation.add(observerText);
    }


    /**
     * notify observers
     */
    @Override
    public  void  notifyObservers(){

        for (IObserverText observerText: playerInformation) {
            observerText.updateOwner(this);

        }
    }

    @Override
    public  void  setScore(int price){
        score += price;
    }

    @Override
    public  int getScore(){
        return  score + money;
    }





}
