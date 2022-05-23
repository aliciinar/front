package com.client.pane.game.space.PurchasableSpace;

import com.client.pane.game.Player.IPlayer;

import com.client.pane.game.space.AbstractSpace;

public  abstract class SpaceDeed extends AbstractSpace {

    protected IPlayer owner = null;
    protected SpaceState state;
    protected int rent;
    protected int cost;

    protected void setOwner(IPlayer player){
        this.owner = player;
    }
    abstract public void Purchase(IPlayer player );

    public int GetCost(){return cost;}
    public int GetRent(){
        return rent;
    }
    public IPlayer GetOwner(){
        return owner;
    }

    @Override
    public String getName() {
        if(owner == null){
            return name + "\nOwner : None" + "\nCost : " + cost + "\nRent : " + rent;
        }
        else {
            return name + "\nOwner : " + owner.getName() + "\nCost : " + cost + "\nRent : " + rent;
        }
    }
}
