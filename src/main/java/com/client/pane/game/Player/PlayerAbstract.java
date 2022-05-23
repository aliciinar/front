package com.client.pane.game.Player;

import com.client.game.Managers.GameManager;
import com.client.pane.game.space.ISpace;
import com.client.pane.game.space.PurchasableSpace.SpaceState;
import javafx.util.Pair;

import java.util.List;

public abstract class PlayerAbstract  implements  IPlayer{

    protected int position;
    protected String name;
    protected int money;
    protected PlayerState state;
    protected int ownedSpecialTile = 0;
    protected  boolean nextTurn = true;
    protected  boolean isPrison = false;
    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void goJail(int pos){
        this.state = new PlayerPrison();
        this.position = pos;
    }

    @Override
    public void purchaseSpecialTile(){
        ownedSpecialTile = ownedSpecialTile + 1;
    }
    @Override
    public int getNumOfSpecialTile(){
        return ownedSpecialTile;
    }
    @Override
    public void moneyTransition(int amount){
        //System.out.println("money azaldı" + money  + " - " + amount);
        money = money + amount;
        //System.out.println("son param" + money);
    }

    @Override
    public void movePlayer(int move){
        //if(position + move >= 16 ) moneyTransition(1500);
        position = (position + move) % 16;
       // System.out.println("Son pozisyounum " + position);


    }

    public String getName() {
        return name;
    }

    @Override
    public boolean turn(int diceValue1 , int diceValue2){
       // Pair<PlayerState,Boolean> newState = state.Play(this,diceValue1,diceValue2);
       // state = newState.getKey();
       // return newState.getValue() ;
        return  true;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void action(ISpace space, int diceVal1, int diceVal2) {
        state =    state.determineState(this,diceVal1,diceVal2);
        Pair<PlayerState,Boolean> result =   state.Play(this,diceVal1,diceVal2,space);
        state = result.getKey();

        System.out.println("***************** new State******************************************** " + state);
        if(result.getValue()){
            GameManager.getInstance().nextTurn();
        }
    }

    @Override
    public boolean isNextTurn() {

        return nextTurn;
    }

    @Override
    public void setNextTurn(boolean nextTurn) {
        System.out.println("Next Turn değiştii " +  nextTurn);
        this.nextTurn = nextTurn;
    }

    @Override
    public boolean isPrison(){
        return  isPrison;
    }
    @Override
    public void  setPrison(boolean isPrison){
        System.out.println("---------------------------------------------------- " + isPrison);
        this.isPrison = isPrison;
    }


}
