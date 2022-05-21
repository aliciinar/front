package com.client.pane.game.Player;

import com.client.pane.game.space.ISpace;

import java.util.List;

public abstract class PlayerAbstract  implements  IPlayer{

    protected int playerID;
    protected int position;
    protected String name;
    protected int money;
    protected PlayerState state;
    protected int ownedSpecialTile = 0;


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
        money = money + amount;
    }

    @Override
    public void movePlayer(int move){
        if(position + move >= 16 ) moneyTransition(1500);
        position = (position + move) % 16;

    }

    public String getName() {
        return name;
    }

    @Override
    public boolean turn(){

        boolean doubleDice;

        doubleDice = state.Play(this );

        if(doubleDice) turn();
        else state = state.EndPlay();

        return money >= 0 ;
    }


}
