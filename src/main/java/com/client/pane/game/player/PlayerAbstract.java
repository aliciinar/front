package com.client.pane.game.player;

import com.client.controller.observer.IObserverText;
import com.client.pane.game.player.playerActionStates.IPlayerState;
import com.client.pane.game.space.ISpace;
import com.client.pane.game.space.purchasableSpace.RailFerrySpace;


import java.util.ArrayList;
import java.util.List;

public abstract class PlayerAbstract  implements  IPlayer{

    // abstract class of the player. general methods and attributes are implemented in here



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



    @Override
    public void purchaseSpecialTile(RailFerrySpace railFerrySpace){ // purchase a special tile  which are railways and ferry port.

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

    @Override
    public void movePlayer(int move){ // set position of the player according to space index
        if((position + move ) > 17){
            moneyTransition(100); // increase money of the player since it is go through start point but not  stop in start point
            startPassMoneyChange = true;
        }
        position = (position + move) % 16;



    }

    @Override
    public boolean purchaseSpace(int spacePrice) { // determine player can buy a space or not
        if(getMoney() > spacePrice){
            return  true;
        }
        
        return  false;
    }


    @Override
    public void action(ISpace space, int diceVal1, int diceVal2) { //  action of the player according the roll values
        state =    state.determineState(this,diceVal1,diceVal2);  // determine the state according to the dice values
        state =   state.Play(this,diceVal1,diceVal2,space); // make action with states


    }

    @Override
    public  void checkFalseMoneyIncrease(boolean jailCheck){ // check whether we increase money falsely or not
        if(jailCheck && startPassMoneyChange){
            moneyTransition( -100);
        }
        startPassMoneyChange = false;

    }

    @Override
    public void  addUserInformation(IObserverText observerText){ //  add observer
        playerInformation.add(observerText);
    }

    @Override
    public  void  notifyObservers(){ // notify observer

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
