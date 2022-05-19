package com.client.game.Spaces.PurchasableSpace;

import com.frontend.Player.IPlayer;
import com.client.game.Spaces.AbstractSpace;

abstract class SpaceDeed extends AbstractSpace {

    protected IPlayer owner = null;
    protected SpaceState state;
    protected int rent;
    protected int cost;

    protected void setOwner(IPlayer player){
        this.owner = player;
    }
    abstract public void Purchase(IPlayer player , int cost);

    public int GetCost(){return cost;}
    public int GetRent(){
        return rent;
    }
    public IPlayer GetOwner(){
        return owner;
    }

}
