package com.client.pane.game.space.PurchasableSpace;

import com.client.pane.game.Player.IPlayer;



public class Property extends SpaceDeed {

    public Property(String name , int cost  ){
        this.name = name;
        this.rent = cost / 10;
        this.cost = cost;
        state = new NotPurchased();
    }

    @Override
    public void action(IPlayer player) {
        state = state.Action(player , this);
    }

    public void Purchase(IPlayer player , int cost){
        player.moneyTransition(cost);
        player.purchaseSpecialTile();
        setOwner(player);

    }


}
