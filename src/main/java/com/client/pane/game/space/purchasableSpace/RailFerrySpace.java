package com.client.pane.game.space.purchasableSpace;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.deedState.NotPurchased;
import com.client.pane.game.space.purchasableSpace.deedState.Purchased;

/**
 * Railways and Ferry port spaces implemented in this class
 */
public class RailFerrySpace extends SpaceDeed {


    public RailFerrySpace(String name , int cost){
        this.name = name;
        this.rent = cost / 10;
        this.cost = cost;
        state = new NotPurchased();
    }


    /**
     *
     * action of the Railways and Ferry port spaces
     * @param player active Player
     */

    @Override
    public void action(IPlayer player) {
        state = state.Action(player , this);
    }


    /**
     *  buy a special tile
     * @param player active player
     */
    public void purchase(IPlayer player ){

        player.moneyTransition(-cost);
        player.purchaseSpecialTile(this); //
        setOwner(player);

        player.setScore(cost);
        state = new Purchased();
    }

    @Override
    public String getName() {
        return super.getName();
    }


    public  void  setRent(int rent,IPlayer player){
        this.rent = rent;
        updateObserver(player);  // notify observers in front end

    }
}

