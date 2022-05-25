package com.client.pane.game.space.purchasableSpace;

import com.client.pane.game.player.IPlayer;
import com.client.pane.game.space.purchasableSpace.deedState.NotPurchased;
import com.client.pane.game.space.purchasableSpace.deedState.Purchased;

public class RailFerrySpace extends SpaceDeed {
    // Railways and Ferry port spaces implemented in this class

    public RailFerrySpace(String name , int cost){
        this.name = name;
        this.rent = cost / 10;
        this.cost = cost;
        state = new NotPurchased();
    }

    @Override
    public void action(IPlayer player) { // action of the Railways and Ferry port spaces

        state = state.Action(player , this);
    }

    public void purchase(IPlayer player ){ // buy this space

        player.moneyTransition(-cost);  // player money should be decrease
        player.purchaseSpecialTile(this); // player buy an special tile so rent of this special tile should be changed
        setOwner(player); // set owner
      //  this.rent = player.getNumOfSpecialTile() * rent; // set rent of this special tile according to the owner special tile count
        player.setScore(cost); // set player new score
        state = new Purchased(); // set new state
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

