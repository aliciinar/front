package com.client.pane.game.player;

import com.client.pane.game.player.playerActionStates.PlayerState;
import com.client.pane.game.space.ISpace;
import javafx.util.Pair;

public abstract class PlayerAbstract  implements  IPlayer{

    protected int position;
    protected String name;
    protected int money;
    protected PlayerState state;
    protected int ownedSpecialTile = 0;
    protected  boolean nextTurn = true;
    protected  boolean isPrison = false;
    protected UserType userType;
    protected  boolean startPassMoneyChange = false; // if we roll double third time and we in pass through start point we have an unneccesary money change
                                                        // this is not sutiable for oop but because of time constraints implementation done like that

    @Override
    public int getMoney() {
        return money;
    }



    @Override
    public void purchaseSpecialTile(){
        ownedSpecialTile = ownedSpecialTile + 1;
    }

    @Override
    public int getNumOfSpecialTile(){
        return ownedSpecialTile;
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

    @Override
    public void moneyTransition(int amount){
        //System.out.println("money azaldı" + money  + " - " + amount);
        System.out.println("Ben Kimim" + getName());
        money = money + amount;

        System.out.println("Param değişti " + amount + " son Param "  + getMoney());
        //System.out.println("son param" + money);
    }

    @Override
    public void movePlayer(int move){
        //if(position + move >= 16 ) moneyTransition(1500);
        if((position + move ) > 17){
            moneyTransition(100); // increase money of the player since it is go through start point but not  stop in start point
            startPassMoneyChange = true;
        }
        position = (position + move) % 16;
       // System.out.println("Son pozisyounum " + position);


    }



    @Override
    public void action(ISpace space, int diceVal1, int diceVal2) {
        state =    state.determineState(this,diceVal1,diceVal2);
        state =   state.Play(this,diceVal1,diceVal2,space);
      //  state = result.getKey();

        System.out.println("***************** new State******************************************** " + state + " " + getName());
       /* if(result.getValue()){
            GameManager.getInstance().nextTurn();
        }*/
    }

    @Override
    public  void checkFalseMoneyIncrease(boolean jailCheck){
        if(jailCheck && startPassMoneyChange){
            moneyTransition( -100);
        }
        startPassMoneyChange = false;

    }








}
