package com.client.pane.game.space.purchasableSpace;

import com.client.pane.game.player.IPlayer;

import com.client.pane.game.space.AbstractSpace;
import com.client.pane.game.space.purchasableSpace.deedState.ISpaceState;

/**
 * abstract class for deed spaces.
 * a deed can be purchased by a player so it has two state which is not purchased and purchased.
 * actions of the player determined according to the deed state
 */
public  abstract class SpaceDeed extends AbstractSpace {

    protected IPlayer owner = null; // owner of the deed
    protected ISpaceState state;  // state of the deed
    protected int rent;  // rent of the dedd
    protected int cost;  // purchase cost of the deed

    protected void setOwner(IPlayer player){

        this.owner = player;
    }
    abstract public void purchase(IPlayer player );

    public int getCost(){return cost;}
    public int getRent(){
        return rent;
    }
    public IPlayer getOwner(){
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
