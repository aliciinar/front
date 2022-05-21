package com.client.game.Player;

import com.client.game.Spaces.ISpace;

import java.util.List;

public abstract class PlayerAbstract  implements  IPlayer{

    protected int playerID;
    protected int position;
    protected int money;
    protected PlayerState state;
    protected int ownedSpecialTile = 0;
    List<ISpace> ISpaces;




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

        this.ISpaces.get(position).action(this);

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
